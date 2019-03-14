package net.nemerosa.gradle.dependency.publication

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.repositories
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class DependencyPublicationTaskTest {

    @Test
    fun `Simple dependencies`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependencyPublicationPlugin::class.java)
        val inMemory = InMemoryDependencyPublisher()
        simpleProject {
            extensions.configure<DependencyPublicationExtension> {
                publisher = inMemory
            }
        } publish {
            val node = inMemory.node
            assertNotNull(node, "Root node is set") { root ->
                val versions = root.dependencies.map {
                    "${it.node.group}:${it.node.name} = ${it.node.version}"
                }.toSet()
                assertEquals(
                        setOf("org.apache.commons:commons-lang3 = 3.8.1"),
                        versions
                )
            }
        }
    }

    @Test
    fun `Dependencies as a string`() {
        val output = StringDependencyTextOutput()
        simpleProject {
            extensions.configure<DependencyPublicationExtension> {
                publisher = TextDependencyPublisher(
                        FlatDependencyTextFormatter(),
                        output
                )
            }
        } publish {
            assertEquals(
                    "org.apache.commons:commons-lang3 = 3.8.1\n",
                    output.value
            )
        }
    }

    private fun simpleProject(
            configuration: Project.() -> Unit
    ): ProjectContext {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependencyPublicationPlugin::class.java)
        project.repositories {
            mavenCentral()
        }
        project.configurations.create("api")
        project.dependencies {
            "api"("org.apache.commons:commons-lang3:3.8.1")
        }
        project.configuration()
        return ProjectContext(project)
    }

    private class ProjectContext(
            private val project: Project
    ) {
        infix fun publish(code: Project.() -> Unit) {
            project.tasks.named<DependencyPublicationTask>("dependencyPublish").get().publish()
            project.code()
        }
    }

}