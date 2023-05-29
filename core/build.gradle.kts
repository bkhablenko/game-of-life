plugins {
    `java-library`

    kotlin("jvm") version "1.8.21"
}

repositories {
    mavenCentral()
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}
