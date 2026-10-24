# git.ps1 - GitHub 傻瓜式上传控制台
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

function Test-GitRepo {
    return (Test-Path ".git")
}

function Get-RepoStatus {
    if (-not (Test-GitRepo)) { return "当前目录不是 Git 仓库" }
    $branch = git rev-parse --abbrev-ref HEAD 2>$null
    $remote = git remote get-url origin 2>$null
    if (-not $remote) { $remote = "（未关联远程）" }
    $dirty = (git status --porcelain | Measure-Object).Count
    return "分支: $branch | 远程: $remote | 未提交改动: $dirty 个"
}

function Show-Menu {
    Clear-Host
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "           GitHub 傻瓜式上传控制台（Minecraft Mod 专用）          " -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host " 工作目录: $(Get-Location)" -ForegroundColor DarkGray
    Write-Host " 仓库状态: $(Get-RepoStatus)" -ForegroundColor DarkGray
    Write-Host "------------------------------------------------------------------" -ForegroundColor Cyan
    Write-Host " [1] 首次初始化仓库（新项目第一次推送到 GitHub 用这个）" -ForegroundColor Yellow
    Write-Host " [2] 日常上传（改了代码，提交并推送）" -ForegroundColor Yellow
    Write-Host " [3] 拉取远程更新（别人改了仓库，或你在别的电脑改了）" -ForegroundColor Green
    Write-Host " [4] 查看当前状态（改了哪些文件、最近提交记录）" -ForegroundColor Gray
    Write-Host " [5] 生成/更新 .gitignore（Minecraft 项目专用）" -ForegroundColor Gray
    Write-Host " [6] 打版本标签并推送（发布用，如 v1.0.0）" -ForegroundColor Magenta
    Write-Host " [7] 新建/切换分支" -ForegroundColor Magenta
    Write-Host " [8] 撤销最近一次提交（保留改动，仅撤销 commit）" -ForegroundColor Red
    Write-Host " [9] 彻底放弃本地改动（危险，需二次确认）" -ForegroundColor Red
    Write-Host " [10] 查看/修改远程地址" -ForegroundColor Gray
    Write-Host " [11] 用浏览器打开 GitHub 仓库页面" -ForegroundColor Gray
    Write-Host " [12] 检查 Git 环境（是否安装、是否配置用户名邮箱）" -ForegroundColor Gray
    Write-Host " [q] 退出" -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
}

function Confirm-Danger($msg) {
    Write-Host ""
    Write-Host "? 危险操作：$msg" -ForegroundColor Red
    $ans = Read-Host "确认请输入 YES（大写），其他任意键取消"
    return ($ans -ceq "YES")
}

function Require-Repo {
    if (-not (Test-GitRepo)) {
        Write-Host ""
        Write-Host "? 当前目录不是 Git 仓库。请先执行 [1] 首次初始化仓库。" -ForegroundColor Red
        Read-Host "按回车返回主菜单" | Out-Null
        return $false
    }
    return $true
}

# ============ 功能实现 ============

function Do-Init {
    if (Test-GitRepo) {
        Write-Host "? 当前目录已经是 Git 仓库了。日常上传请用 [2]。" -ForegroundColor Red
        Read-Host "按回车返回" | Out-Null
        return
    }
    Write-Host ""
    Write-Host "=== 首次初始化仓库 ===" -ForegroundColor Cyan
    Write-Host "请先在 GitHub 网页上新建一个空仓库（不要勾选 README/.gitignore/LICENSE），"
    Write-Host "然后把仓库地址（HTTPS 或 SSH）准备好。"
    Write-Host ""
    $remote = Read-Host "请输入 GitHub 仓库地址（例: https://github.com/用户名/仓库名.git）"
    if ([string]::IsNullOrWhiteSpace($remote)) {
        Write-Host "? 地址为空，取消。" -ForegroundColor Red
        Read-Host "按回车返回" | Out-Null
        return
    }

    Write-Host ""
    Write-Host "→ git init" -ForegroundColor Yellow
    git init | Out-Null

    Write-Host "→ 生成 .gitignore（Minecraft 项目专用）" -ForegroundColor Yellow
    Write-MinecraftGitignore

    Write-Host "→ git add -A" -ForegroundColor Yellow
    git add -A | Out-Null

    $msg = Read-Host "请输入首次提交信息（直接回车默认: Initial commit）"
    if ([string]::IsNullOrWhiteSpace($msg)) { $msg = "Initial commit" }
    Write-Host "→ git commit" -ForegroundColor Yellow
    git commit -m "$msg" | Out-Null

    Write-Host "→ git branch -M main" -ForegroundColor Yellow
    git branch -M main 2>$null

    Write-Host "→ git remote add origin $remote" -ForegroundColor Yellow
    git remote add origin $remote

    Write-Host "→ git push -u origin main" -ForegroundColor Yellow
    Write-Host "（如果弹出登录窗口，请用 GitHub 账号登录或粘贴 Personal Access Token）" -ForegroundColor DarkGray
    git push -u origin main

    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "? 初始化完成。以后改代码直接用 [2] 日常上传。" -ForegroundColor Green
    } else {
        Write-Host ""
        Write-Host "? push 失败。常见原因：远程地址错、没登录、仓库非空。" -ForegroundColor Red
        Write-Host "  排查：git remote -v 看地址；git push -u origin main 看完整报错。" -ForegroundColor DarkGray
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-DailyPush {
    if (-not (Require-Repo)) { return }

    Write-Host ""
    Write-Host "=== 日常上传 ===" -ForegroundColor Cyan
    Write-Host "当前改动：" -ForegroundColor DarkGray
    git status --short
    Write-Host ""

    $dirty = (git status --porcelain | Measure-Object).Count
    if ($dirty -eq 0) {
        Write-Host "没有未提交的改动。" -ForegroundColor DarkGray
        $ans = Read-Host "是否仍要 push 一次？(y/N)"
        if ($ans -ne "y") { return }
    } else {
        $msg = Read-Host "请输入提交信息（直接回车默认: 更新时间戳）"
        if ([string]::IsNullOrWhiteSpace($msg)) {
            $msg = "Update $(Get-Date -Format 'yyyy-MM-dd HH:mm')"
        }
        Write-Host "→ git add -A" -ForegroundColor Yellow
        git add -A | Out-Null
        Write-Host "→ git commit -m `"$msg`"" -ForegroundColor Yellow
        git commit -m "$msg" | Out-Null
    }

    Write-Host "→ git push" -ForegroundColor Yellow
    git push

    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "? 上传完成。" -ForegroundColor Green
    } else {
        Write-Host ""
        Write-Host "? push 失败。最常见原因：远程有你本地没有的提交。" -ForegroundColor Red
        Write-Host "  建议先执行 [3] 拉取远程更新，再回来 [2]。" -ForegroundColor DarkGray
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-Pull {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 拉取远程更新 ===" -ForegroundColor Cyan
    $dirty = (git status --porcelain | Measure-Object).Count
    if ($dirty -gt 0) {
        Write-Host "? 本地有未提交改动。pull 可能冲突。" -ForegroundColor Yellow
        $ans = Read-Host "是否先 stash 暂存本地改动？(y/N)"
        if ($ans -eq "y") {
            git stash push -m "auto-stash $(Get-Date -Format 'yyyyMMddHHmmss')"
            Write-Host "→ 已暂存。pull 完成后可用 git stash pop 恢复。" -ForegroundColor DarkGray
        }
    }
    Write-Host "→ git pull --rebase" -ForegroundColor Yellow
    git pull --rebase
    if ($LASTEXITCODE -eq 0) {
        Write-Host "? 拉取完成。" -ForegroundColor Green
    } else {
        Write-Host "? 拉取冲突。请手动解决冲突后 git rebase --continue。" -ForegroundColor Red
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-Status {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 当前状态 ===" -ForegroundColor Cyan
    Write-Host "--- 改动文件 ---" -ForegroundColor DarkGray
    git status --short
    Write-Host ""
    Write-Host "--- 最近 5 条提交 ---" -ForegroundColor DarkGray
    git log --oneline -5
    Read-Host "按回车返回" | Out-Null
}

function Write-MinecraftGitignore {
    $ignore = @'
# Gradle
.gradle/
build/
!gradle/wrapper/gradle-wrapper.jar
out/

# IDE
.idea/
*.iml
*.ipr
*.iws
.vscode/
.settings/
.classpath
.project
bin/

# Minecraft 运行目录
run/
runs/

# 日志与临时文件
*.log
logs/
*.tmp
*.bak

# 系统
.DS_Store
Thumbs.db
desktop.ini

# 本地私密配置
*.token
secrets.properties
local.properties

# 反编译/临时解压产物
decompiled/
*src_backup*/

# 参考源码，不上传
reference/
'@
    if (Test-Path ".gitignore") {
        Copy-Item ".gitignore" ".gitignore.bak" -Force
        Write-Host "→ 已备份原 .gitignore 为 .gitignore.bak" -ForegroundColor DarkGray
    }
    Set-Content -Path ".gitignore" -Value $ignore -Encoding UTF8
}

function Do-Gitignore {
    Write-Host ""
    Write-Host "=== 生成 .gitignore ===" -ForegroundColor Cyan
    Write-Host "将写入 Minecraft Mod 项目通用规则：Gradle 产物、IDE 文件、run 目录、日志等。" -ForegroundColor DarkGray
    $ans = Read-Host "确认写入？(y/N)"
    if ($ans -eq "y") {
        Write-MinecraftGitignore
        Write-Host "? .gitignore 已写入。如果它已被 git 跟踪，需要 git rm --cached 后重新提交。" -ForegroundColor Green
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-Tag {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 打版本标签 ===" -ForegroundColor Cyan
    Write-Host "当前已有标签（最近5个）：" -ForegroundColor DarkGray
    git tag --sort=-creatordate | Select-Object -First 5
    Write-Host ""
    $tag = Read-Host "请输入标签名（例: v1.0.0）"
    if ([string]::IsNullOrWhiteSpace($tag)) { return }
    $msg = Read-Host "标签说明（可留空）"
    if ([string]::IsNullOrWhiteSpace($msg)) {
        git tag "$tag"
    } else {
        git tag -a "$tag" -m "$msg"
    }
    Write-Host "→ git push origin $tag" -ForegroundColor Yellow
    git push origin "$tag"
    if ($LASTEXITCODE -eq 0) {
        Write-Host "? 标签已推送。去 GitHub Releases 页面可基于此标签发布版本。" -ForegroundColor Green
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-Branch {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 分支管理 ===" -ForegroundColor Cyan
    Write-Host "本地分支：" -ForegroundColor DarkGray
    git branch
    Write-Host ""
    Write-Host " [1] 新建并切换到新分支"
    Write-Host " [2] 切换到已有分支"
    Write-Host " [q] 返回"
    $c = Read-Host "选择"
    if ($c -eq "1") {
        $name = Read-Host "新分支名（例: feature/ritual-system）"
        if (-not [string]::IsNullOrWhiteSpace($name)) {
            git checkout -b "$name"
            Write-Host "? 已创建并切换到 $name" -ForegroundColor Green
        }
    } elseif ($c -eq "2") {
        $name = Read-Host "目标分支名"
        if (-not [string]::IsNullOrWhiteSpace($name)) {
            git checkout "$name"
        }
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-UndoCommit {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 撤销最近一次提交（保留改动）===" -ForegroundColor Cyan
    Write-Host "效果：撤销 commit，但文件改动保留在工作区，可以重新修改后再提交。" -ForegroundColor DarkGray
    git log --oneline -1
    if (-not (Confirm-Danger "撤销最近一次提交")) { return }
    git reset --soft HEAD~1
    Write-Host "? 已撤销 commit。改动还在，可以用 [2] 重新提交。" -ForegroundColor Green
    Read-Host "按回车返回" | Out-Null
}

function Do-HardReset {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 彻底放弃本地改动 ===" -ForegroundColor Red
    Write-Host "效果：所有未提交的改动全部丢失，不可恢复。" -ForegroundColor Red
    git status --short
    if (-not (Confirm-Danger "丢弃所有未提交改动，无法恢复")) { return }
    git reset --hard HEAD
    git clean -fd
    Write-Host "? 已恢复到最近一次提交的状态。" -ForegroundColor Green
    Read-Host "按回车返回" | Out-Null
}

function Do-Remote {
    if (-not (Require-Repo)) { return }
    Write-Host ""
    Write-Host "=== 远程地址 ===" -ForegroundColor Cyan
    git remote -v
    Write-Host ""
    $ans = Read-Host "是否修改 origin 地址？(y/N)"
    if ($ans -eq "y") {
        $url = Read-Host "新地址"
        if (-not [string]::IsNullOrWhiteSpace($url)) {
            git remote set-url origin "$url"
            Write-Host "? 已修改。新地址：" -ForegroundColor Green
            git remote -v
        }
    }
    Read-Host "按回车返回" | Out-Null
}

function Do-OpenBrowser {
    if (-not (Require-Repo)) { return }
    $url = git remote get-url origin 2>$null
    if (-not $url) {
        Write-Host "? 未配置远程地址。" -ForegroundColor Red
        Read-Host "按回车返回" | Out-Null
        return
    }
    $url = $url -replace '\.git$', ''
    $url = $url -replace '^git@github\.com:', 'https://github.com/'
    Write-Host "→ 打开 $url" -ForegroundColor Yellow
    Start-Process $url
}

function Do-CheckEnv {
    Write-Host ""
    Write-Host "=== Git 环境检查 ===" -ForegroundColor Cyan
    $gitVer = git --version 2>$null
    if (-not $gitVer) {
        Write-Host "? 未检测到 git。请先安装 Git for Windows：https://git-scm.com/download/win" -ForegroundColor Red
        Read-Host "按回车返回" | Out-Null
        return
    }
    Write-Host "? $gitVer" -ForegroundColor Green

    $name = git config --global user.name
    $email = git config --global user.email
    if ([string]::IsNullOrWhiteSpace($name)) {
        Write-Host "? 未配置 user.name" -ForegroundColor Red
        $n = Read-Host "请输入要配置的用户名（GitHub 显示名）"
        if (-not [string]::IsNullOrWhiteSpace($n)) { git config --global user.name "$n" }
    } else {
        Write-Host "? user.name = $name" -ForegroundColor Green
    }
    if ([string]::IsNullOrWhiteSpace($email)) {
        Write-Host "? 未配置 user.email" -ForegroundColor Red
        $e = Read-Host "请输入要配置的邮箱（GitHub 绑定邮箱）"
        if (-not [string]::IsNullOrWhiteSpace($e)) { git config --global user.email "$e" }
    } else {
        Write-Host "? user.email = $email" -ForegroundColor Green
    }

    $cred = git config --global credential.helper
    if ($cred) {
        Write-Host "? credential.helper = $cred" -ForegroundColor Green
    } else {
        Write-Host "! 未配置 credential.helper，push 时可能反复要求输入密码。" -ForegroundColor Yellow
        Write-Host "  推荐执行：git config --global credential.helper manager" -ForegroundColor DarkGray
    }
    Read-Host "按回车返回" | Out-Null
}

# ============ 主循环 ============
Show-Menu

while ($true) {
    Write-Host ""
    $choice = Read-Host "请输入数字并按回车"
    if ([string]::IsNullOrWhiteSpace($choice)) { continue }
    $choice = $choice.Trim()

    switch ($choice) {
        "1"  { Do-Init }
        "2"  { Do-DailyPush }
        "3"  { Do-Pull }
        "4"  { Do-Status }
        "5"  { Do-Gitignore }
        "6"  { Do-Tag }
        "7"  { Do-Branch }
        "8"  { Do-UndoCommit }
        "9"  { Do-HardReset }
        "10" { Do-Remote }
        "11" { Do-OpenBrowser }
        "12" { Do-CheckEnv }
        "q"  { Write-Host "已退出。" -ForegroundColor Yellow; break }
        default { Write-Host "输入无效，请输入 1-12 或 q" -ForegroundColor Red }
    }
    if ($choice -eq "q") { break }
    Show-Menu
}