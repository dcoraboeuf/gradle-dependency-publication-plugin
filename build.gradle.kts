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

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}
