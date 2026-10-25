# 启动工具箱.ps1 - 主控台
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

# 自动获取当前脚本所在的目录路径（Mod工具包）
$ToolboxPath = $PSScriptRoot

# 定义三个子模块的路径
$ModuleGradle = Join-Path $ToolboxPath "Gradle脚本.ps1"
$ModuleGitHub = Join-Path $ToolboxPath "GitHub脚本.ps1"
$ModuleDecompile = Join-Path $ToolboxPath "反编译脚本.ps1"

function Show-MainMenu {
    Clear-Host
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "                 Mod 开发工具箱 - 主控台                         " -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host " 当前工作目录: $(Get-Location)" -ForegroundColor DarkGray
    Write-Host " 工具箱位置:   $ToolboxPath" -ForegroundColor DarkGray
    Write-Host "------------------------------------------------------------------" -ForegroundColor Cyan
    Write-Host " [1] 进入 Gradle 模块（构建 / 运行 / 编译）" -ForegroundColor Yellow
    Write-Host " [2] 进入 GitHub 模块（提交 / 推送 / 发布）" -ForegroundColor Green
    Write-Host " [3] 进入反编译模块（CFR 反编译 / 目录树生成）" -ForegroundColor Magenta
    Write-Host " [q] 退出主控台" -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
}

function Invoke-Module($ModulePath, $ModuleName) {
    if (-not (Test-Path $ModulePath)) {
        Write-Host "未找到模块文件: $ModuleName" -ForegroundColor Red
        Write-Host "请检查路径是否正确: $ModulePath" -ForegroundColor DarkGray
        Read-Host "按回车返回..." | Out-Null
        return
    }
    
    Write-Host "正在加载 $ModuleName ..." -ForegroundColor DarkGray
    Start-Sleep -Milliseconds 300
    
    # 执行子脚本
    & $ModulePath
    
    Write-Host ""
    Write-Host "$ModuleName 已退出。" -ForegroundColor DarkGray
    Read-Host "按回车返回主控台..." | Out-Null
}

# 首次显示菜单
Show-MainMenu

while ($true) {
    Write-Host ""
    $choice = (Read-Host "请输入指令编号并按回车").Trim()
    if ([string]::IsNullOrWhiteSpace($choice)) { continue }

    switch ($choice) {
        "1" { Invoke-Module $ModuleGradle "Gradle 模块" }
        "2" { Invoke-Module $ModuleGitHub "GitHub 模块" }
        "3" { Invoke-Module $ModuleDecompile "反编译模块" }
        "q" { Write-Host "已退出主控台。" -ForegroundColor Yellow; break }
        default { Write-Host "指令无效，请输入 1、2、3 或 q。" -ForegroundColor Red }
    }
    
    if ($choice -eq "q") { break }
    Show-MainMenu
}