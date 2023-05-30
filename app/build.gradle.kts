plugins {
    application

    kotlin("jvm") version "1.8.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))

    // https://github.com/ajalt/mordant
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta13")

    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
}

application {
    mainClass.set("com.github.bkhablenko.ApplicationKt")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    test {
        useJUnitPlatform()
    }
}
