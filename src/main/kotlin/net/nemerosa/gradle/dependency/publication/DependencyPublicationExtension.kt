package net.nemerosa.gradle.dependency.publication

class DependencyPublicationExtension {

    var subprojects = true

    /**
     * Configurations eligible for publication
     */
    var configurations = mutableSetOf("api", "implementation", "compile", "runtime")

}
