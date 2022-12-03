plugins {
    id("jacoco")
    id("pmd")
    id("advent-of-code-2022.java-conventions")
    id("advent-of-code-2022.code-metrics")
    id("advent-of-code-2022.publishing-conventions")
    id("com.diffplug.spotless") version "6.12.0" apply true
    kotlin("jvm") version "1.7.22" apply false
    kotlin("plugin.spring") version "1.7.22" apply false
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
        maven {
            url = uri("https://maven.pkg.github.com/yonatankarp/advent-of-code-2022")
            credentials {
                username = findProperty("gpr.user")?.toString() ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("gpr.key")?.toString() ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
