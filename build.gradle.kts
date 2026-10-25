plugins {
    // ★ 核心修复：去掉了 version "2.2.0"，避免和 gtnhconvention 冲突
    kotlin("jvm") 
    id("com.gtnewhorizons.gtnhconvention")
}

// 使用 findProperty 安全读取版本号
version = findProperty("modVersion")?.toString() ?: "1.0.0"

// 在 minecraft 块里注入版本号到 Tags 类
minecraft {
    injectedTags.put("VERSION", project.version)
}

repositories {
    maven {
        name = "GTNH Maven"
        url = uri("https://nexus.gtnewhorizons.com/repository/public/")
    }
    mavenCentral()
    mavenLocal()
}

// 打包配置 (CoreMod 设置)
tasks.jar {
    manifest {
        attributes(
            "FMLAT" to "thaumicsurvival_at.cfg",
            "FMLCorePluginContainsFMLMod" to "true"
        )
    }
}

dependencies {
    // Kotlin 标准库
    implementation(kotlin("stdlib"))
}

// 告诉 Kotlin 插件：源码直接去 src/main/java 和 src/test/java 里面找
kotlin {
    sourceSets["main"].kotlin.srcDir("src/main/java")
    sourceSets["test"].kotlin.srcDir("src/test/java")
}