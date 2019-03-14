package net.nemerosa.gradle.dependency.publication

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType

open class DependencyPublicationTask : DefaultTask() {

    @Input
    var subprojects: Boolean? = null

    /**
     * Configurations eligible for publication
     */
    @Input
    var configurations: Set<String>? = null

    /**
     * Overriding extension configurations?
     */
    @Input
    var overrideConfigurations: Boolean = false


    @TaskAction
    fun publish() {
        // Gets the extension
        val extension = project.extensions.getByType(DependencyPublicationExtension::class)
        // Root project
        val rootNode = project.createDependencyNode()
        // Collection for the root project at the end
        collectProject(rootNode, extension, project)
        // List of projects to consider
        val subprojects = subprojects ?: extension.subprojects
        if (subprojects) {
            project.subprojects {
                // Creates a node for this project
                val subprojectNode = createDependencyNode()
                // Collection
                collectProject(subprojectNode, extension, this)
                // TODO Adds a "subproject" dependency
            }
        }
        // TODO Publication of the data
    }

    private fun collectProject(node: DependencyNode, extension: DependencyPublicationExtension, project: Project) {
        project.configurations.forEach { configuration ->
            if (isConfigurationEligible(extension, configuration)) {
                collectConfiguration(node, extension, project, configuration)
            }
        }
    }

    private fun collectConfiguration(
            node: DependencyNode,
            extension: DependencyPublicationExtension,
            project: Project,
            configuration: Configuration
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun isConfigurationEligible(extension: DependencyPublicationExtension, configuration: Configuration): Boolean {
        val configurations: Set<String> = this.configurations
                ?.run {
                    if (overrideConfigurations) {
                        this
                    } else {
                        extension.configurations + this
                    }
                }
                ?: extension.configurations
        return configuration.name in configurations
    }

    private fun Project.createDependencyNode() = DependencyNode(
            group = group.toString()
    )

}