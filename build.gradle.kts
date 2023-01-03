import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    `java-library`
    `maven-publish`
    signing
    id("xyz.jpenilla.run-paper") version "2.0.1"
    id("io.papermc.paperweight.userdev") version "1.4.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "com.kalimero2.team"
version = "1.0.0-SNAPSHOT"
description = "Plugin Template"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    paperDevBundle("1.19.3-R0.1-SNAPSHOT")
    bukkitLibrary("cloud.commandframework", "cloud-paper", "1.8.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

bukkit {
    main = "com.kalimero2.team.template.TemplatePlugin"
    apiVersion = "1.19"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    authors = listOf("Author")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    runServer {
        minecraftVersion("1.19.3")
    }
}

publishing {
    repositories {
        maven {
            name = "byquantonRepo"
            if(version.toString().endsWith("-SNAPSHOT")) {
                url = uri("https://repo.byquanton.eu/snapshots")
            } else {
                url = uri("https://repo.byquanton.eu/releases")
            }
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

