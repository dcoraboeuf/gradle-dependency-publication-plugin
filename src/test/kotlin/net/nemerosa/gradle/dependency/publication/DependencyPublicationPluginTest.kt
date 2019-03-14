package net.nemerosa.gradle.dependency.publication

import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class DependencyPublicationPluginTest {

    @Test
    fun `dependencyPublication task is declared`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(DependencyPublicationPlugin::class.java)
        val task = project.tasks.findByName("dependencyPublish")
        assertNotNull(task, "dependencyPublish task is declared")
    }

}