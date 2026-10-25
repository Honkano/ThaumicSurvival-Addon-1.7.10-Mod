# 封装一个显示菜单的函数，方便反复调用
function Show-Menu {
    Clear-Host
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "        Minecraft GTNH Mod 开发控制台 (PowerShell 版)             " -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host " [0] stop           停止 Gradle 守护进程 (手动兜底)" -ForegroundColor DarkYellow
    Write-Host " [1] clean build    重置并重新编译 (失败自动诊断+必要时 stop 重试)" -ForegroundColor Yellow
    Write-Host " [2] build          常规编译+打包 (产物在 build/libs/)" -ForegroundColor Yellow
    Write-Host " [3] spotlessApply  自动格式化代码 (GTNH 必跑，提交前必用)" -ForegroundColor Green
    Write-Host " [4] runClient      启动 Minecraft 客户端进行测试" -ForegroundColor Magenta
    Write-Host " [5] runServer      启动 Minecraft 服务端" -ForegroundColor Magenta
    Write-Host " [6] clean          清理 build/ 文件夹" -ForegroundColor DarkGray
    Write-Host " [7] dependencies   查看依赖树 (排查冲突)" -ForegroundColor DarkGray
    Write-Host " [8] --stacktrace   带完整错误堆栈的构建 (定位报错首选)" -ForegroundColor Red
    Write-Host " [9] tasks          查看当前项目所有可执行任务" -ForegroundColor DarkGray
    Write-Host " [q] 退出控制台" -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
}

# ============================================================
# 智能 clean build：先跑，失败了判断是不是文件被锁，再决定重试
# ============================================================
function Invoke-SmartCleanBuild {
    Write-Host "开始 clean build ..." -ForegroundColor Yellow

    # 2>&1 合并 stderr；Tee-Object 既显示到屏幕又收集到 $rawLog
    $rawLog = & .\gradlew.bat clean build 2>&1 | Tee-Object -Variable logLines
    $exitCode = $LASTEXITCODE

    if ($exitCode -eq 0) {
        Write-Host "clean build 成功。" -ForegroundColor Green
        return
    }

    # 构建失败，判断日志里是否出现"文件被占用/无法删除"类关键字
    $logText = ($logLines | Out-String)

    $lockPatterns = @(
        'Unable to delete',
        'Could not delete',
        'Failed to delete',
        'being used by another process',
        'cannot delete',
        '另一个程序正在使用此文件',
        '无法删除',
        '拒绝访问'
    )

    $isLockIssue = $false
    foreach ($p in $lockPatterns) {
        if ($logText -match [regex]::Escape($p)) {
            $isLockIssue = $true
            break
        }
    }

    if ($isLockIssue) {
        Write-Host ""
        Write-Host "检测到 build/ 文件被占用或无法删除，判断是 Gradle 守护进程锁住了。" -ForegroundColor Yellow
        Write-Host "正在停止守护进程并自动重试一次..." -ForegroundColor Yellow

        .\gradlew.bat --stop

        Write-Host "重试 clean build ..." -ForegroundColor Yellow
        .\gradlew.bat clean build

        if ($LASTEXITCODE -eq 0) {
            Write-Host "重试后 clean build 成功。" -ForegroundColor Green
        } else {
            Write-Host "重试后仍然失败，请查看上方完整日志排查（可能是代码或依赖问题）。" -ForegroundColor Red
        }
    } else {
        Write-Host ""
        Write-Host "构建失败，但日志里没有发现文件被占用的迹象，判断不是锁文件问题，不自动重试。" -ForegroundColor Red
        Write-Host "建议用 [8] --stacktrace 查看具体报错。" -ForegroundColor Red
    }
}

# 首次显示菜单
Show-Menu

while ($true) {
    Write-Host ""
    $input = Read-Host "请输入数字并按回车执行"
    $input = $input.Trim()

    if ([string]::IsNullOrWhiteSpace($input)) { continue }

    switch ($input) {
        "0" { 
            Write-Host "正在停止 Gradle 守护进程..." -ForegroundColor Yellow
            .\gradlew.bat --stop 
        }
        "1" { Invoke-SmartCleanBuild }
        "2" { .\gradlew.bat build }
        "3" { 
            Write-Host "?? 警告：spotlessApply 会重写文件，请确保已 Commit！" -ForegroundColor Red
            .\gradlew.bat spotlessApply 
        }
        "4" { .\gradlew.bat runClient }
        "5" { .\gradlew.bat runServer }
        "6" { .\gradlew.bat clean }
        "7" { .\gradlew.bat dependencies }
        "8" { .\gradlew.bat build --stacktrace }
        "9" { .\gradlew.bat tasks }
        "q" {
            Write-Host "已退出控制台。祝你写 Mod 顺利！" -ForegroundColor Yellow
            break
        }
        default {
            Write-Host "输入错误，请重新输入 0-9 或 q" -ForegroundColor Red
        }
    }
    
    if ($input -eq "q") { break }

    Write-Host ""
    Read-Host "任务已结束，按回车键返回主菜单..." | Out-Null
    Show-Menu 
}