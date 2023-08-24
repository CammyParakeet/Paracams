plugins {
    `java-library`
    `maven-publish`
}

group = "com.parakeetstudios.parabots"
version = "1.0.0-SNAPSHOT"
description = "API for Paracams library/plugin"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.parakeetstudios"
            artifactId = "paracams-api"
            version = "1.0.0"
        }
    }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

}