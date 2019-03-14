plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    id("net.nemerosa.versioning") version "2.8.2"
}

group = "net.nemerosa"
version = versioning.info.display

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("dependencyPublication") {
            id = "net.nemerosa.dependencyPublication"
            implementationClass = "net.nemerosa.gradle.dependency.publication.DependencyPublicationPlugin"
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}
