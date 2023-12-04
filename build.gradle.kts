import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    id("java-gradle-plugin")
    id("maven-publish")
    application
    antlr
}

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


group = "io.github.AlexanderTaran73"
version = "0.0.1"
gradlePlugin {
    plugins {
        create("dirinf") {
            id = "io.github.AlexanderTaran73"
            implementationClass = "DirInfPlugin"
        }
    }
}