# decompile-tool.ps1 - CFR 反编译 & 目录树生成（傻瓜式增强版）
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

$script:CfrJarPath = ""

# ==================== 通用工具 ====================
function Pause-Return { Read-Host "按回车返回主菜单..." | Out-Null }

function Show-MainMenu {
    Clear-Host
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "        CFR 反编译 & 目录树生成控制台（Minecraft Mod 专用）        " -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host " 当前目录: $(Get-Location)" -ForegroundColor DarkGray
    if ($script:CfrJarPath -and (Test-Path $script:CfrJarPath)) {
        Write-Host " CFR 路径: $script:CfrJarPath" -ForegroundColor DarkGray
    } else {
        Write-Host " CFR 路径: 未设置（请先按 [3]）" -ForegroundColor DarkYellow
    }
    Write-Host "------------------------------------------------------------------" -ForegroundColor Cyan
    Write-Host " [1] CFR 反编译（批量 jar / 自定义输出名 / 高级选项）" -ForegroundColor Yellow
    Write-Host " [2] 生成目录结构树（自定义文件名，存当前目录）" -ForegroundColor Green
    Write-Host " [3] 设置 / 修改 CFR 路径" -ForegroundColor Gray
    Write-Host " [4] CFR 功能说明 & 新手常见问题（看不懂选项时按这个）" -ForegroundColor DarkCyan
    Write-Host " [q] 退出" -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
}

# ==================== CFR 路径设置 ====================
function Set-CfrPath {
    Write-Host ""
    Write-Host "=== 设置 CFR 路径 ===" -ForegroundColor Cyan
    Write-Host "请输入 cfr-0.152.jar 的完整路径（可以拖拽文件到窗口）" -ForegroundColor DarkGray
    $p = (Read-Host "CFR 路径").Trim().Trim("'").Trim('"')
    if ([string]::IsNullOrWhiteSpace($p) -or -not (Test-Path $p)) {
        Write-Host "路径为空或文件不存在，未修改。" -ForegroundColor Red
        Pause-Return; return
    }
    $script:CfrJarPath = (Resolve-Path $p).Path
    Write-Host "已设置：$script:CfrJarPath" -ForegroundColor Green
    Pause-Return
}

function Require-Cfr {
    if (-not $script:CfrJarPath -or -not (Test-Path $script:CfrJarPath)) {
        Write-Host "尚未设置有效的 CFR 路径。请先使用 [3] 设置。" -ForegroundColor Red
        Pause-Return; return $false
    }
    return $true
}

# ==================== 高级选项配置 ====================
function Get-CfrAdvancedOptions {
    Write-Host ""
    Write-Host "=== CFR 高级选项（直接回车 = 使用默认，推荐新手直接回车）===" -ForegroundColor Cyan
    Write-Host "说明：这些选项大部分情况下不需要动。拿不准就一路回车。" -ForegroundColor DarkGray
    Write-Host ""

    # 1. 编码
    Write-Host "[1] 输出编码" -ForegroundColor Yellow
    Write-Host "    默认 utf8。如果反编译后中文是乱码，说明原 jar 可能用了 GBK 等编码。" -ForegroundColor DarkGray
    $enc = Read-Host "    编码（回车=utf8）"
    if ([string]::IsNullOrWhiteSpace($enc)) { $enc = "utf8" }

    # 2. 是否保留注释
    Write-Host ""
    Write-Host "[2] 是否生成 CFR 注释" -ForegroundColor Yellow
    Write-Host "    默认 false（干净）。true 会在代码里插入 CFR 自己加的注释，一般不需要。" -ForegroundColor DarkGray
    $comments = Read-Host "    生成注释？(true/false，回车=false)"
    if ([string]::IsNullOrWhiteSpace($comments)) { $comments = "false" }

    # 3. 是否静默
    Write-Host ""
    Write-Host "[3] 静默模式" -ForegroundColor Yellow
    Write-Host "    默认 true（只输出结果，不刷进度）。调试时才需要 false。" -ForegroundColor DarkGray
    $silent = Read-Host "    静默？(true/false，回车=true)"
    if ([string]::IsNullOrWhiteSpace($silent)) { $silent = "true" }

    # 4. 额外 classpath
    Write-Host ""
    Write-Host "[4] 补充依赖路径（--extraclasspath）" -ForegroundColor Yellow
    Write-Host "    如果反编译时大量报“找不到类/符号”，可以指向模组的 libs 目录或包含依赖 jar 的文件夹。" -ForegroundColor DarkGray
    Write-Host "    例：C:\projects\MyMod\libs   或   .\dependencies" -ForegroundColor DarkGray
    $extraCp = Read-Host "    依赖路径（回车=不指定）"

    # 5. 类过滤
    Write-Host ""
    Write-Host "[5] 类过滤（--enableclassfilter）" -ForegroundColor Yellow
    Write-Host "    处理非常大的 fat jar 时，跳过 Spring/Guava 等第三方库，能显著加快速度。" -ForegroundColor DarkGray
    Write-Host "    注意：如果目标 mod 本身依赖这些库且你需要看它们，不要开。" -ForegroundColor DarkGray
    $classFilter = Read-Host "    启用类过滤？(y/N，回车=N)"
    $useClassFilter = ($classFilter -eq "y" -or $classFilter -eq "Y")

    # 6. 解码 String switch
    Write-Host ""
    Write-Host "[6] 解码 String switch（--decodestringswitch）" -ForegroundColor Yellow
    Write-Host "    默认开启。让 switch 语句更接近源码，一般保持 true 即可。" -ForegroundColor DarkGray
    $strSwitch = Read-Host "    解码 String switch？(true/false，回车=true)"
    if ([string]::IsNullOrWhiteSpace($strSwitch)) { $strSwitch = "true" }

    # 7. 移除样板代码
    Write-Host ""
    Write-Host "[7] 移除编译器样板代码（--removeboilerplate）" -ForegroundColor Yellow
    Write-Host "    默认 false。开启后会去掉一些编译器自动生成的冗余代码，输出更干净。" -ForegroundColor DarkGray
    $removeBoiler = Read-Host "    移除样板代码？(true/false，回车=false)"
    if ([string]::IsNullOrWhiteSpace($removeBoiler)) { $removeBoiler = "false" }

    # 8. 非法标识符重命名
    Write-Host ""
    Write-Host "[8] 重命名非法标识符（--renameillegalidents）" -ForegroundColor Yellow
    Write-Host "    如果反编译出来的代码里有奇怪的名字（如 var1$），开启后会更像正常 Java 代码。" -ForegroundColor DarkGray
    $renameIllegal = Read-Host "    重命名非法标识符？(true/false，回车=false)"
    if ([string]::IsNullOrWhiteSpace($renameIllegal)) { $renameIllegal = "false" }

    # 组装参数
    $opts = @(
        "--outputencoding", $enc,
        "--comments", $comments,
        "--silent", $silent,
        "--decodestringswitch", $strSwitch,
        "--removeboilerplate", $removeBoiler,
        "--renameillegalidents", $renameIllegal
    )
    if (-not [string]::IsNullOrWhiteSpace($extraCp)) {
        $opts += @("--extraclasspath", $extraCp.Trim().Trim("'").Trim('"'))
    }
    if ($useClassFilter) {
        $opts += "--enableclassfilter"
    }

    return $opts
}

# ==================== [1] CFR 反编译 ====================
function Invoke-CfrDecompile {
    if (-not (Require-Cfr)) { return }

    Write-Host ""
    Write-Host "=== CFR 反编译 ===" -ForegroundColor Cyan

    # 目标目录
    $targetDir = (Read-Host "请输入要扫描的目录路径（会递归查找 .jar）").Trim().Trim("'").Trim('"')
    if ([string]::IsNullOrWhiteSpace($targetDir) -or -not (Test-Path $targetDir)) {
        Write-Host "目录不存在，返回。" -ForegroundColor Red; Pause-Return; return
    }

    # 查找 jar
    Write-Host "正在扫描 .jar 文件..." -ForegroundColor DarkGray
    $jars = @(Get-ChildItem -Path $targetDir -Filter "*.jar" -Recurse -File -ErrorAction SilentlyContinue)
    if ($jars.Count -eq 0) {
        Write-Host "未找到任何 .jar 文件。" -ForegroundColor Red; Pause-Return; return
    }

    # 列出并选择
    Write-Host ""
    Write-Host "找到以下 jar 文件：" -ForegroundColor Gray
    for ($i = 0; $i -lt $jars.Count; $i++) {
        $rel = $jars[$i].FullName.Substring((Resolve-Path $targetDir).Path.Length).TrimStart('\')
        Write-Host ("  [{0}] {1}" -f ($i + 1), $rel) -ForegroundColor DarkGray
    }
    Write-Host ""
    Write-Host " [a] 处理全部" -ForegroundColor Yellow
    Write-Host " [n] 只处理指定编号（逗号分隔，如 1,3,5）" -ForegroundColor Yellow
    $mode = Read-Host "选择"

    $selected = @()
    if ($mode -eq "a" -or $mode -eq "A") {
        $selected = $jars
    } elseif ($mode -eq "n" -or $mode -eq "N") {
        $nums = Read-Host "输入编号（逗号分隔）"
        foreach ($n in $nums.Split(',')) {
            $idx = 0
            if ([int]::TryParse($n.Trim(), [ref]$idx) -and $idx -ge 1 -and $idx -le $jars.Count) {
                $selected += $jars[$idx - 1]
            }
        }
        if ($selected.Count -eq 0) {
            Write-Host "未选择有效编号，返回。" -ForegroundColor Red; Pause-Return; return
        }
    } else {
        Write-Host "无效选择，返回。" -ForegroundColor Red; Pause-Return; return
    }

    # 输出根目录
    Write-Host ""
    $defaultOut = Join-Path (Get-Location) "decompiled"
    $outRoot = (Read-Host "输出根目录（回车默认: $defaultOut）").Trim().Trim("'").Trim('"')
    if ([string]::IsNullOrWhiteSpace($outRoot)) { $outRoot = $defaultOut }

    # 命名方式
    Write-Host ""
    Write-Host "为每个 jar 指定输出文件夹名：" -ForegroundColor DarkGray
    Write-Host " [1] 自动用 jar 文件名" -ForegroundColor Gray
    Write-Host " [2] 自定义前缀 + jar 文件名" -ForegroundColor Gray
    $nameMode = Read-Host "选择"
    $prefix = ""
    if ($nameMode -eq "2") { $prefix = Read-Host "请输入前缀（如: botania_）" }

    # 高级选项
    Write-Host ""
    $askAdv = Read-Host "是否进入高级选项？(y/N，直接回车=N 使用默认推荐配置)"
    $advancedOpts = @()
    if ($askAdv -eq "y" -or $askAdv -eq "Y") {
        $advancedOpts = Get-CfrAdvancedOptions
    } else {
        # 默认推荐配置（和你上一版一样，最稳）
        $advancedOpts = @(
            "--outputencoding", "utf8",
            "--comments", "false",
            "--silent", "true"
        )
    }

    # 执行
    $ok = 0; $fail = 0; $failedList = @()

    foreach ($jar in $selected) {
        $base = [System.IO.Path]::GetFileNameWithoutExtension($jar.Name)
        $folder = if ($nameMode -eq "2" -and -not [string]::IsNullOrWhiteSpace($prefix)) { "$prefix$base" } else { $base }
        $outDir = Join-Path $outRoot $folder

        Write-Host ""
        Write-Host "----------------------------------------" -ForegroundColor DarkGray
        Write-Host "处理: $($jar.FullName)" -ForegroundColor Yellow
        Write-Host "输出: $outDir" -ForegroundColor DarkGray

        $allArgs = @("-jar", $script:CfrJarPath, $jar.FullName, "--outputdir", $outDir) + $advancedOpts

        $output = & java @allArgs 2>&1
        $exit = $LASTEXITCODE

        if ($exit -eq 0) {
            Write-Host "[OK] 成功" -ForegroundColor Green; $ok++
        } else {
            Write-Host "[FAIL] 失败（退出码 $exit）" -ForegroundColor Red; $fail++
            $failedList += $jar.FullName
            if ($output) {
                Write-Host "错误摘要（最后 5 行）：" -ForegroundColor DarkGray
                $output | Select-Object -Last 5 | ForEach-Object { Write-Host "  $_" -ForegroundColor DarkGray }
            }
        }
    }

    # 汇总
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "完成。成功: $ok，失败: $fail" -ForegroundColor Cyan
    if ($fail -gt 0) {
        Write-Host "失败的 jar：" -ForegroundColor Red
        $failedList | ForEach-Object { Write-Host "  - $_" -ForegroundColor Red }
        Write-Host ""
        Write-Host "常见原因与处理：" -ForegroundColor Yellow
        Write-Host "  · 大量“找不到类” → 用 [4] 的 extraclasspath 指向依赖目录" -ForegroundColor DarkGray
        Write-Host "  · jar 本身损坏/加密 → 换一个 jar 试试" -ForegroundColor DarkGray
        Write-Host "  · 极端混淆 → 反编译本来就不可能完美，能读逻辑就够了" -ForegroundColor DarkGray
    }
    Write-Host "========================================" -ForegroundColor Cyan
    Pause-Return
}

# ==================== [2] 目录树 ====================
function Invoke-DirectoryTree {
    Write-Host ""
    Write-Host "=== 生成目录结构树 ===" -ForegroundColor Cyan

    $targetDir = (Read-Host "请输入要生成树的根目录路径").Trim().Trim("'").Trim('"')
    if ([string]::IsNullOrWhiteSpace($targetDir) -or -not (Test-Path $targetDir -PathType Container)) {
        Write-Host "目录不存在或不是文件夹，返回。" -ForegroundColor Red; Pause-Return; return
    }
    $targetDir = (Resolve-Path $targetDir).Path

    $fileName = (Read-Host "文件名（回车默认: tree，会自动加 .txt）").Trim()
    if ([string]::IsNullOrWhiteSpace($fileName)) { $fileName = "tree" }
    if (-not $fileName.EndsWith(".txt")) { $fileName += ".txt" }

    $outputPath = Join-Path (Get-Location) $fileName
    Write-Host "输出: $outputPath" -ForegroundColor DarkGray
    Write-Host "正在生成..." -ForegroundColor Yellow

    $lines = New-Object System.Collections.Generic.List[string]
    $lines.Add("目录树: $targetDir")
    $lines.Add("生成时间: $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')")
    $lines.Add("=" * 60)

    function Add-TreeLevel {
        param([string]$Path, [string]$Prefix = "")
        $items = Get-ChildItem -LiteralPath $Path -Force -ErrorAction SilentlyContinue |
                 Sort-Object { $_.PSIsContainer }, Name -Descending
        for ($i = 0; $i -lt $items.Count; $i++) {
            $item = $items[$i]
            $last = ($i -eq $items.Count - 1)
            $conn = if ($last) { "└── " } else { "├── " }
            $newPre = if ($last) { "$Prefix    " } else { "$Prefix│   " }
            $name = if ($item.PSIsContainer) { "$($item.Name)/" } else { $item.Name }
            $lines.Add("$Prefix$conn$name")
            if ($item.PSIsContainer) { Add-TreeLevel -Path $item.FullName -Prefix $newPre }
        }
    }
    Add-TreeLevel -Path $targetDir

    try {
        $lines | Out-File -FilePath $outputPath -Encoding UTF8 -Force
        Write-Host "[OK] 已生成：$outputPath（$($lines.Count) 行）" -ForegroundColor Green
    } catch {
        Write-Host "[FAIL] 写入失败：$($_.Exception.Message)" -ForegroundColor Red
    }
    Pause-Return
}

# ==================== [4] 说明与常见问题 ====================
function Show-CfrHelp {
    Clear-Host
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host "                    CFR 新手说明 & 常见问题                       " -ForegroundColor Cyan
    Write-Host "==================================================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "【日常最推荐的用法】" -ForegroundColor Green
    Write-Host "  反编译时，高级选项直接回车（默认配置）。" -ForegroundColor Gray
    Write-Host "  默认配置 = utf8 编码 + 不生成注释 + 静默模式，最稳、最干净。" -ForegroundColor DarkGray
    Write-Host ""
    Write-Host "【什么时候需要动高级选项？】" -ForegroundColor Yellow
    Write-Host "  · 中文乱码 → 把输出编码从 utf8 改成 GBK 试试" -ForegroundColor DarkGray
    Write-Host "  · 大量“找不到类” → 设置 extraclasspath 指向模组的 libs/ 或依赖文件夹" -ForegroundColor DarkGray
    Write-Host "  · 反编译超大 fat jar 很慢 → 开 --enableclassfilter（但可能跳过你想看的库）" -ForegroundColor DarkGray
    Write-Host "  · 代码里变量名很怪（var1$）→ 开 --renameillegalidents" -ForegroundColor DarkGray
    Write-Host "  · 想看 CFR 自己加的注释 → 把 comments 改成 true" -ForegroundColor DarkGray
    Write-Host ""
    Write-Host "【为什么反编译出来的代码不能直接编译？】" -ForegroundColor Yellow
    Write-Host "  反编译是“猜”源码，不是还原源码。变量名、注释、泛型细节可能丢失，" -ForegroundColor DarkGray
    Write-Host "  Lambda/switch 的还原也可能和原写法不同。用来读逻辑、抄思路没问题，" -ForegroundColor DarkGray
    Write-Host "  想直接扔回项目里编译，通常还要手动修一些类型错误。" -ForegroundColor DarkGray
    Write-Host ""
    Write-Host "【Minecraft 模组反编译特别提示】" -ForegroundColor Yellow
    Write-Host "  · 很多模组 jar 是混淆过的（ProGuard），反编译出来可能是 a/b/c 这种名字。" -ForegroundColor DarkGray
    Write-Host "  · 这是正常的，CFR 已经尽力了。配合 mappings（如果提供）会更好读。" -ForegroundColor DarkGray
    Write-Host "  · CFR 对 Lambda 和现代 Java 的支持是目前最好的之一。" -ForegroundColor DarkGray
    Write-Host ""
    Write-Host "【忘了某个选项是什么意思？】" -ForegroundColor Yellow
    Write-Host "  可以在终端直接运行：java -jar cfr-0.152.jar --help" -ForegroundColor DarkGray
    Write-Host "  会列出全部官方参数和简短说明。" -ForegroundColor DarkGray
    Write-Host ""
    Write-Host "==================================================================" -ForegroundColor Cyan
    Pause-Return
}

# ==================== 主循环 ====================
Show-MainMenu
while ($true) {
    Write-Host ""
    $choice = (Read-Host "请输入数字并按回车").Trim()
    if ([string]::IsNullOrWhiteSpace($choice)) { continue }

    switch ($choice) {
        "1" { Invoke-CfrDecompile }
        "2" { Invoke-DirectoryTree }
        "3" { Set-CfrPath }
        "4" { Show-CfrHelp }
        "q" { Write-Host "已退出。" -ForegroundColor Yellow; break }
        default { Write-Host "输入无效，请输入 1、2、3、4 或 q" -ForegroundColor Red }
    }
    if ($choice -eq "q") { break }
    Show-MainMenu
}