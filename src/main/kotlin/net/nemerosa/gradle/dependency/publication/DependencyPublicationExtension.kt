package net.nemerosa.gradle.dependency.publication

import org.gradle.api.Project
import java.io.File

open class DependencyPublicationExtension(
        private val project: Project
) {

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

    /**
     * Configures a [TextDependencyPublisher]
     */
    fun text(config: TextDependencyPublisherContext.() -> Unit) {
        val context = TextDependencyPublisherContext()
        context.config()
        publisher = context()
    }

    inner class TextDependencyPublisherContext {

        private var file: File? = null

        fun toFile(path: String) {
            file = project.file(path)
        }

        operator fun invoke(): TextDependencyPublisher {
            val file = this.file
            val output: DependencyTextOutput = when {
                file != null -> FileDependencyTextOutput(file)
                else -> StringDependencyTextOutput()
            }
            return TextDependencyPublisher(
                    formatter = FlatDependencyTextFormatter(),
                    output = output
            )
        }

    }

}
