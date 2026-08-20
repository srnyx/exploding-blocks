plugins {
    java
    id("xyz.srnyx.gradle-galaxy") version "db0a4c4"
    id("com.gradleup.shadow") version "9.6.1"
    id("me.modmuss50.mod-publish-plugin") version "675051c"
    id("io.papermc.hangar-publish-plugin") version "0.1.4"
    id("xyz.jpenilla.run-paper") version "3.1.0"
}

group = "xyz.srnyx"
description = "Every block you break will explode"

galaxy {
    minecraft {
        spigotAPI("1.14.2")
        annoyingAPI("dd4052c")

        dependency {
            optional {
                repositories.add(PLACEHOLDER_API)
                group = "me.clip"
                artifact = "placeholderapi"
                version = "2.12.2"

                pluginYml = "PlaceholderAPI"
                modrinth = "placeholderapi"
                hangar = "PlaceholderAPI"
            }
        }

        pluginYml {
            developerData(SRNYX)
            permissionPrefix = "eb"

            command("eb") {
                aliases.add("explodingblocks")
                description = "Toggles the Exploding Blocks plugin"

                permission("toggle")
            }
        }

        platformPublishing {
            github("srnyx/exploding-blocks")
            modrinth("kKXUJTU9")
            hangar("ExplodingBlocks")
            spigot("104482")
            curseforge("896921")

            projectData("exploding-blocks")
        }
    }
}
