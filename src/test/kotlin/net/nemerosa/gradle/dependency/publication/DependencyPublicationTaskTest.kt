package net.nemerosa.gradle.dependency.publication

import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
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
        project.extensions.configure<DependencyPublicationExtension> {
            publisher = inMemory
        }
        project.configurations.create("api")
        project.dependencies {
            "api"("group:name:1.0")
        }
        project.tasks.named<DependencyPublicationTask>("dependencyPublish").get().publish()
        val node = inMemory.node
        assertNotNull(node, "Root node is set") { root ->
            val versions = root.dependencies.map {
                "${it.node.group}:${it.node.name} = ${it.node.version}"
            }.toSet()
            assertEquals(
                    setOf("group:name = 1.0"),
                    versions
            )
        }
    }

}