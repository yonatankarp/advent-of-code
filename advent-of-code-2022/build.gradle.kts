plugins {
    id("jacoco")
    id("advent-of-code-2022.code-metrics")
    kotlin("jvm")
    kotlin("plugin.spring")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
}

tasks {
    getByName<Jar>("jar") {
        enabled = false
    }

    build {
        finalizedBy(spotlessApply)
    }

    withType<Test> {
        useJUnitPlatform()
        finalizedBy(spotlessApply)
        finalizedBy(jacocoTestReport)
        finalizedBy(pmdTest)
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    jacocoTestReport {
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}
