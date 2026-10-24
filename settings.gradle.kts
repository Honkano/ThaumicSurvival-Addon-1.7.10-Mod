// ============================================================================
// settings.gradle.kts：Gradle 的“设置阶段”脚本。
//
// 它比 build.gradle 更早执行，负责两件事：
//   1. 告诉 Gradle 去哪里找“构建插件”（pluginManagement）；
//   2. 告诉 Gradle 去哪里找“普通依赖”（dependencyResolutionManagement）。
// 最后再声明本项目使用的 settings 约定插件（plugins）。
//
// 注意：这个文件里的仓库只影响“设置阶段”和“全局依赖解析”。
// build.gradle 里那个空的 repositories { } 仍然是“额外仓库”入口，
// 两边会合并生效，不要以为这里写了就不用管那边。
// ============================================================================

pluginManagement {
    // 这里只决定“plugins { } 里的插件”从哪下载，不影响普通依赖。
    // 顺序有意义：Gradle 会按声明顺序依次尝试，找到就停。
    repositories {
        // 1) 阿里云 Gradle 插件镜像。
        //    放第一位是为了国内网络下优先走镜像，避免直连 Gradle Plugin Portal 超时。
        //    它只镜像插件门户，不镜像普通 Maven 依赖。
        maven {
            name = "Aliyun Gradle Plugin"
            url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        }

        // 2) GTNH 自己的 Maven。
        //    mavenContent 是“内容过滤”：只允许这个仓库去解析 com.gtnewhorizons
        //    及其子包下的构件。没有这层过滤，Gradle 可能拿每个插件请求都来问它一遍，
        //    既慢又容易拉到不该拉的东西。
        //    includeGroupByRegex 里的 \\. 是正则转义的点，表示包名分隔符。
        maven {
            name = "GTNH Maven"
            url = uri("https://nexus.gtnewhorizons.com/repository/public/")
            mavenContent {
                includeGroup("com.gtnewhorizons")
                includeGroupByRegex("com\\.gtnewhorizons\\..+")
            }
        }

        // 3) Gradle 官方插件门户。前面镜像和 GTNH 都找不到时的兜底。
        gradlePluginPortal()

        // 4) Maven Central。有些插件不在门户里，但在 Central 上。
        mavenCentral()

        // 5) 本地 Maven 仓库（~/.m2/repository）。
        //    放最后是合理的：只有你本地手动 install 过某个插件时才需要它，
        //    放太前会掩盖远程仓库的问题。
        mavenLocal()
    }
}

dependencyResolutionManagement {
    // 这里决定“普通依赖”（build.gradle 里 api / implementation / compileOnly 等）
    // 从哪下载，不影响 plugins { } 的插件解析。
    //
    // 注意：这里没有放阿里云镜像，只放了 GTNH、Central、mavenLocal。
    // 这是当前配置的现状，不是漏配——普通依赖主要来自 GTNH 生态和 Central，
    // 而且 gradle.properties 里 includeWellKnownRepositories = true 还会
    // 自动补上 CurseMaven、Modrinth 等仓库，两边会合并。
    repositories {
        // GTNH Maven：TC4/Baubles/NEI 这类 GTNH 生态构件的主要来源。
        // 这里没有加 mavenContent 过滤，因为普通依赖确实可能从它拉各种东西；
        // 如果你发现解析变慢，可以像上面 pluginManagement 那样加 includeGroup 收窄。
        maven {
            name = "GTNH Maven"
            url = uri("https://nexus.gtnewhorizons.com/repository/public/")
        }

        // Maven Central：通用 Java 库的默认来源。
        mavenCentral()

        // 本地 Maven 仓库。放最后，理由同上：只作兜底，不优先。
        mavenLocal()
    }
}

plugins {
    // GTNH 的 settings 约定插件。
    // 它负责把 GTNH 项目在“设置阶段”的公共配置统一起来
    // （仓库约定、版本对齐、与 buildscript 的配合等）。
    // 版本 2.0.20 是当前模板配套版本，一般跟随模板升级，不要单独乱改。
    // 它和 gradle.properties 里的 gtnh.settings.blowdryerTag 是两个东西：
    // 前者管 settings 阶段约定，后者管 Spotless/格式化规则版本，别混。
    id("com.gtnewhorizons.gtnhsettingsconvention") version("2.0.20")
}