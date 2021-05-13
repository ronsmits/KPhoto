import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 */
plugins {
    kotlin("jvm") version "1.5.0"
    `java-library`
    id("org.openjfx.javafxplugin") version "0.0.9"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.4.20"
    signing
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<JavaCompile> {
    options.release.set(11)
}
repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("no.tornado:tornadofx2:2.0.0-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.20-RC")
    implementation("net.coobird:thumbnailator:[0.4, 0.5)")
    implementation("com.drewnoakes:metadata-extractor:2.10.0")
    implementation("javax.json:javax.json-api:1.1.2")
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.20-RC")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.4.20-RC")
}

javafx {
    version = "15.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.swing", "javafx.web", "javafx.media")
}
group = "nl.codetribe"
version = "1.0-SNAPSHOT"
description = "KPhoto"
java.sourceCompatibility = JavaVersion.VERSION_15
java.targetCompatibility = JavaVersion.VERSION_15

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
