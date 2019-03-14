package net.nemerosa.gradle.dependency.publication

data class DependencyNode(
        val group: String,
        val name: String,
        val version: String
) {

    val dependencies = mutableMapOf<String, MutableList<DependencyNode>>()

    fun dependency(link: String, child: DependencyNode) {
        val children = dependencies[link]
        if (children == null) {
            dependencies[link] = mutableListOf(child)
        } else {
            children += child
        }
    }
}
