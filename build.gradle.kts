plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

// 使用 findProperty 安全读取
version = findProperty("modVersion")?.toString() ?: "1.0.0"

// 在 minecraft 块里确保注入
minecraft {
    // ...
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

tasks.jar {
    manifest {
        attributes(
            "FMLAT" to "thaumicsurvival_at.cfg",
            "FMLCorePluginContainsFMLMod" to "true"
        )
    }
}