plugins {
    alias(libs.plugins.blossom)
    alias(libs.plugins.shadowJar)
    java
}

var displayName = "ExtensionManager"

group = "com.github.klainstom"
version = "1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.minestom)
}

tasks {
    blossom {
        replaceToken("&NAME", displayName.toUpperCase())
        replaceToken("&Name", displayName)
        replaceToken("&name", displayName.toLowerCase())
        replaceToken("&version", version)
        replaceToken("&minestomVersion", libs.versions.minestom.get())
    }

    processResources {
        expand(
            mapOf(
                "name" to displayName,
                "version" to version
            )
        )
    }

    shadowJar {
        archiveBaseName.set(displayName)
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
    }

    test {
        useJUnitPlatform()
    }

    build {
        dependsOn(shadowJar)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}