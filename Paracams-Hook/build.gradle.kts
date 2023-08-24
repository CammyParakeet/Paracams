plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.1"
}

group = "com.parakeetstudios.paracams"
version = "1.0.0-SNAPSHOT"
description = "Plugin hook for Paracams library/api"

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation(project(":Paracams-API"))
    implementation(project(":Paracams-Core", configuration = "reobf"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.shadowJar)
            groupId = "com.parakeetstudios"
            artifactId = "paracams"
            version = "1.0.0"
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

tasks {
    assemble{
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}

tasks.shadowJar {
    archiveBaseName.set("paracams")
    archiveVersion.set("1.0.0")
    archiveClassifier.set("")
    dependsOn(project(":Paracams-Core").tasks.getByName("reobfJar"))
}