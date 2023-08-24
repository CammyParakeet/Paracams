plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.5.5"
}

group = "com.parakeetstudios.parabots"
version = "1.0.0-SNAPSHOT"
description = "Core implementation for Paracams library/plugin"

dependencies {
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
    compileOnly(project(":Paracams-API"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.parakeetstudios"
            artifactId = "paracams-core"
            version = "1.0.0"
        }
    }
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}