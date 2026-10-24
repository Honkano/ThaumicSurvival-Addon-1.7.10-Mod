# 封装一个显示菜单的函数，方便反复调用
function Show-Menu {
    Clear-Host
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "        Minecraft GTNH Mod 开发控制台 (PowerShell 版)             " -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host " [1] clean build    重置并重新编译 (遇到诡异问题时最彻底)" -ForegroundColor Yellow
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

# 首次显示菜单
Show-Menu

while ($true) {
    Write-Host ""
    $input = Read-Host "请输入数字并按回车执行"
    
    # 去掉输入前后的空格
    $input = $input.Trim()

    # 如果是空回车，直接跳过，重新等输入
    if ([string]::IsNullOrWhiteSpace($input)) { continue }

    switch ($input) {
        "1" { .\gradlew.bat clean build }
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
            Write-Host "输入错误，请重新输入 1-9 或 q" -ForegroundColor Red
        }
    }
    
    # 如果输入了 q 就真正退出
    if ($input -eq "q") { break }

    # ----- 关键体验优化 -----
    Write-Host ""
    # 这一句会让你有时间看完编译日志，按回车后才继续
    Read-Host "任务已结束，按回车键返回主菜单..." | Out-Null
    # 清屏并重新显示菜单
    Show-Menu 
}