package net.nemerosa.gradle.dependency.publication

open class DependencyPublicationExtension {

    var subprojects = true

    /**
     * Configurations eligible for publication
     */
    var configurations = mutableSetOf("api", "implementation", "compile", "runtime")

    /**
     * Name of the "subproject" relationship
     */
    var subprojectRelName: String = "subproject"

    /**
     * Publisher
     */
    var publisher: DependencyPublisher = TextDependencyPublisher(
            formatter = FlatDependencyTextFormatter(),
            output = StdOutDependencyTextOutput()
    )

}
