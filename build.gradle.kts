plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

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
