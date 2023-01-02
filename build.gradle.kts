plugins {
    id("jacoco")
    id("pmd")
    id("com.diffplug.spotless") version "6.12.1"
    val kotlinVersion = "1.8.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}


repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.github.kittinunf.fuel:fuel:2.3.1") // For downloading input file
    implementation("com.github.kittinunf.result:result:5.3.0") // Needed for Fuel
    implementation("io.github.furstenheim:copy_down:1.1") // Convert puzzle description to markdown

    val junitVersion = "5.9.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

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

