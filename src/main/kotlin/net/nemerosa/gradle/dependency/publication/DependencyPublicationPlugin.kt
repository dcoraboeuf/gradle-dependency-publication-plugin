package net.nemerosa.gradle.dependency.publication

import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.kotlin.dsl.register

@Suppress("unused")
class DependencyPublicationPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        // Creates the dependency publication task at root level
        project.tasks.register<DependencyPublicationTask>("dependencyPublish")

    }

}
