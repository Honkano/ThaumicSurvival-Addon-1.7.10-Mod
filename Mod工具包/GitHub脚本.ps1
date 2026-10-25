# git.ps1 - GitHub 傻瓜式上传控制台
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

function Test-GitRepo {
    return (Test-Path ".git")
}

# ============ 新增：检测 gh 是否就绪（不安装也不影响 [6] 原模式）============
function Test-GhReady {
    $gh = Get-Command gh -ErrorAction SilentlyContinue
    if (-not $gh) {
        Write-Host ""
        Write-Host "? 未检测到 GitHub CLI (gh)，无法自动创建 Release。" -ForegroundColor Red
        Write-Host ""
        Write-Host "如需使用此模式，请先安装并登录（只需一次）：" -ForegroundColor Yellow
        Write-Host "  1) 安装：winget install --id GitHub.cli" -ForegroundColor DarkGray
        Write-Host "  2) 登录：gh auth login" -ForegroundColor DarkGray
        Write-Host ""
        Write-Host "提示：不安装也不影响 [6] 的 [1] 原模式（只打标签推送）。" -ForegroundColor DarkGray
        return $false
    }
    gh auth status 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host ""
        Write-Host "? gh 已安装但未登录。" -ForegroundColor Red
        Write-Host "  请先在终端执行：gh auth login" -ForegroundColor DarkGray
        return $false
    }
    return $true
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
    Write-Host " [6] 打版本标签并推送（可选：同时自动创建 Release 上传 jar）" -ForegroundColor Magenta
    Write-Host " [7] 新建/切换分支" -ForegroundColor Magenta
    Write-Host " [8] 撤销最近一次提交（保留改动，仅撤销 commit）" -ForegroundColor Red
    Write-Host " [9] 彻底放弃本地改动（危险，需二次确认）" -ForegroundColor Red
    Write-Host " [10] 查看/修改远程地址" -ForegroundColor Gray
    Write-Host " [11] 用浏览器打开 GitHub 仓库页面" -ForegroundColor Gray
    Write-Host " [12] 检查 Git 环境（是否安装、是否配置用户名邮箱）" -ForegroundColor Gray
    Write-Host " [13] 更多工具（小抽屉：Release 管理、日志、清理分支等）" -ForegroundColor DarkCyan
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

# ============ [6] 打版本标签（双模式）============
function Do-Tag {
    if (-not (Require-Repo)) { return }

    Write-Host ""
    Write-Host "=== 打版本标签 ===" -ForegroundColor Cyan
    Write-Host " [1] 仅打标签并推送（原模式，默认）" -ForegroundColor Yellow
    Write-Host " [2] 打标签 + 自动创建 Release 并上传 jar（需要 GitHub CLI）" -ForegroundColor Magenta
    Write-Host " [q] 返回" -ForegroundColor Gray
    $mode = Read-Host "选择模式（直接回车 = 1）"
    if ([string]::IsNullOrWhiteSpace($mode)) { $mode = "1" }
    $mode = $mode.Trim()

    if ($mode -eq "q") { return }
    if ($mode -ne "1" -and $mode -ne "2") {
        Write-Host "输入无效，返回。" -ForegroundColor Red
        Read-Host "按回车返回" | Out-Null
        return
    }

    # 选模式 2 先检测 gh，没装就提示并返回（不破坏任何东西）
    if ($mode -eq "2") {
        if (-not (Test-GhReady)) {
            Read-Host "按回车返回" | Out-Null
            return
        }
    }

    Write-Host ""
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
    if ($LASTEXITCODE -ne 0) {
        Write-Host "? 标签推送失败，Release 未创建。" -ForegroundColor Red
        Read-Host "按回车返回" | Out-Null
        return
    }
    Write-Host "? 标签已推送。" -ForegroundColor Green

    # ---------- 原模式到此结束 ----------
    if ($mode -eq "1") {
        Write-Host "去 GitHub Releases 页面可基于此标签发布版本。" -ForegroundColor Green
        Read-Host "按回车返回" | Out-Null
        return
    }

    # ---------- 新模式：创建 Release 并上传 jar ----------
    Invoke-CreateRelease -Tag $tag
    Read-Host "按回车返回" | Out-Null
}

# ============ 新增：创建 Release 的辅助函数 ============
function Invoke-CreateRelease {
    param([string]$Tag)

    Write-Host ""
    Write-Host "=== 创建 GitHub Release ===" -ForegroundColor Cyan

    # 找主产物 jar
    Write-Host "正在查找 build/libs/ 下的主产物 jar..." -ForegroundColor DarkGray
    $jars = @()
    if (Test-Path "build/libs") {
        $jars = @(Get-ChildItem "build/libs" -Filter "*.jar" -File |
            Where-Object { $_.Name -notmatch '-(sources|dev|javadoc)\.jar$' })
    }
    if ($jars.Count -eq 0) {
        Write-Host "? 未找到合适的 jar（已排除 -sources / -dev / -javadoc）。" -ForegroundColor Yellow
        $ans = Read-Host "是否继续创建不含附件的 Release？(y/N)"
        if ($ans -ne "y") { return }
    } else {
        Write-Host "找到以下 jar：" -ForegroundColor Gray
        $jars | ForEach-Object { Write-Host "  - $($_.Name)" -ForegroundColor DarkGray }
    }

    # 发布说明
    Write-Host ""
    Write-Host "--- 发布说明 ---" -ForegroundColor Cyan
    Write-Host " [1] 手写多行说明（空行结束）" -ForegroundColor Gray
    Write-Host " [2] 从文件读取（.md / .txt）" -ForegroundColor Gray
    Write-Host " [g] 自动生成 + 手写前缀（留空前缀则纯自动生成）" -ForegroundColor Gray
    Write-Host " [直接回车] 完全留空，由 GitHub 自动生成" -ForegroundColor Gray
    $noteMode = Read-Host "选择"
    $notes = ""
    $notesFile = $null
    $autoNotes = $false

    switch ($noteMode.Trim().ToLower()) {
        "1" {
            Write-Host "逐行输入，空行结束：" -ForegroundColor DarkGray
            $lines = @()
            while ($true) {
                $l = Read-Host
                if ([string]::IsNullOrWhiteSpace($l)) { break }
                $lines += $l
            }
            $notes = $lines -join "`n"
        }
        "2" {
            $path = Read-Host "输入文件路径"
            if ((-not [string]::IsNullOrWhiteSpace($path)) -and (Test-Path $path)) {
                $notesFile = (Resolve-Path $path).Path
                Write-Host "→ 将从 $notesFile 读取发布说明" -ForegroundColor Gray
            } else {
                Write-Host "? 文件不存在，改为自动生成。" -ForegroundColor Red
                $autoNotes = $true
            }
        }
        "g" {
            $autoNotes = $true
            Write-Host "输入前缀（空行结束，留空则纯自动生成）：" -ForegroundColor DarkGray
            $lines = @()
            while ($true) {
                $l = Read-Host
                if ([string]::IsNullOrWhiteSpace($l)) { break }
                $lines += $l
            }
            $notes = $lines -join "`n"
        }
        default {
            $autoNotes = $true
        }
    }

    $title = Read-Host "Release 标题（直接回车默认: $Tag）"
    if ([string]::IsNullOrWhiteSpace($title)) { $title = $Tag }

    $ghArgs = @("release", "create", $Tag, "--title", $title)
    if ($jars.Count -gt 0) { $ghArgs += $jars.FullName }
    if ($autoNotes) { $ghArgs += "--generate-notes" }
    if ($notesFile) {
        $ghArgs += @("--notes-file", $notesFile)
    } elseif (-not [string]::IsNullOrWhiteSpace($notes)) {
        $ghArgs += @("--notes", $notes)
    }

    Write-Host ""
    Write-Host "→ gh release create $Tag ..." -ForegroundColor Yellow
    & gh @ghArgs

    if ($LASTEXITCODE -eq 0) {
        $repoUrl = git remote get-url origin 2>$null
        $repoUrl = $repoUrl -replace '\.git$', '' -replace '^git@github\.com:', 'https://github.com/'
        Write-Host ""
        Write-Host "? Release 已发布：$repoUrl/releases/tag/$Tag" -ForegroundColor Green
        $open = Read-Host "是否用浏览器打开 Release 页面？(y/N)"
        if ($open -eq "y") { Start-Process "$repoUrl/releases/tag/$Tag" }
    } else {
        Write-Host ""
        Write-Host "? Release 创建失败。常见原因：同标签 Release 已存在、jar 路径含特殊字符。" -ForegroundColor Red
        Write-Host "  排查：gh release view $Tag   或   gh auth status" -ForegroundColor DarkGray
    }
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

    # 顺手报一下 gh 状态（不影响原有流程）
    $gh = Get-Command gh -ErrorAction SilentlyContinue
    if ($gh) {
        Write-Host "? gh 已安装：$(& gh --version 2>$null | Select-Object -First 1)" -ForegroundColor Green
    } else {
        Write-Host "! 未安装 gh（可选，[6] 的新模式需要）。安装：winget install --id GitHub.cli" -ForegroundColor Yellow
    }
    Read-Host "按回车返回" | Out-Null
}

# ============ [13] 小抽屉：不常用但好用的工具 ============
function Do-ToolDrawer {
    Clear-Host
    Write-Host "=== 更多工具（小抽屉）===" -ForegroundColor DarkCyan
    Write-Host " [1] 查看/管理 Release（列表、删除、上传额外文件，需要 gh）" -ForegroundColor Gray
    Write-Host " [2] 查看提交日志（最近 20 条，带图）" -ForegroundColor Gray
    Write-Host " [3] 清理已合并的本地分支" -ForegroundColor Gray
    Write-Host " [4] 查看 jar 产物列表（build/libs/）" -ForegroundColor Gray
    Write-Host " [5] Git 仓库优化（gc + 统计信息）" -ForegroundColor Gray
    Write-Host " [q] 返回主菜单" -ForegroundColor Cyan
    $c = Read-Host "选择"

    switch ($c) {
        "1" {
            if (-not (Require-Repo)) { return }
            if (-not (Test-GhReady)) { Read-Host "按回车返回" | Out-Null; return }
            Write-Host ""
            Write-Host "--- 最近 Release ---" -ForegroundColor DarkGray
            gh release list 2>$null
            Write-Host ""
            $sub = Read-Host "[d] 删除某个 Release / [u] 上传额外文件 / 回车返回"
            if ($sub -eq "d") {
                $t = Read-Host "要删除的标签（会同时删除 Release 和标签）"
                if (-not [string]::IsNullOrWhiteSpace($t)) {
                    if (Confirm-Danger "删除 Release 和标签 $t") {
                        gh release delete "$t" --yes --cleanup-tag
                    }
                }
            } elseif ($sub -eq "u") {
                $t = Read-Host "目标标签"
                $f = Read-Host "要上传的文件路径（可多个，空格分隔）"
                if ($t -and $f) { gh release upload "$t" $f --clobber }
            }
            Read-Host "按回车返回" | Out-Null
        }
        "2" {
            if (-not (Require-Repo)) { return }
            git log --oneline --graph --decorate -20
            Read-Host "按回车返回" | Out-Null
        }
        "3" {
            if (-not (Require-Repo)) { return }
            Write-Host "已合并到当前分支的本地分支：" -ForegroundColor DarkGray
            git branch --merged | Where-Object { $_ -notmatch '^\*' }
            $b = Read-Host "输入要删除的分支名（可多个，空格分隔，回车取消）"
            if (-not [string]::IsNullOrWhiteSpace($b)) {
                git branch -d $b.Split(" ")
            }
            Read-Host "按回车返回" | Out-Null
        }
        "4" {
            if (Test-Path "build/libs") {
                Get-ChildItem "build/libs" -Filter "*.jar" -File |
                    Select-Object Name, @{N="大小(MB)";E={[math]::Round($_.Length/1MB,2)}}, LastWriteTime |
                    Format-Table -AutoSize
            } else {
                Write-Host "build/libs/ 不存在，请先 build。" -ForegroundColor Red
            }
            Read-Host "按回车返回" | Out-Null
        }
        "5" {
            if (-not (Require-Repo)) { return }
            Write-Host "→ git gc --aggressive --prune=now" -ForegroundColor Yellow
            git gc --aggressive --prune=now
            Write-Host "→ 仓库统计" -ForegroundColor Yellow
            git count-objects -vH
            Read-Host "按回车返回" | Out-Null
        }
    }
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
        "13" { Do-ToolDrawer }
        "q"  { Write-Host "已退出。" -ForegroundColor Yellow; break }
        default { Write-Host "输入无效，请输入 1-13 或 q" -ForegroundColor Red }
    }
    if ($choice -eq "q") { break }
    Show-Menu
}