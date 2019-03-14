package net.nemerosa.gradle.dependency.publication

data class DependencyNode(
        val group: String,
        val name: String,
        val version: String
) {

    val dependencies: MutableList<DependencyLink> = mutableListOf()

    fun dependency(link: String, child: DependencyNode) {
        val existingLink = dependencies.find {
            it.node == child
        }
        if (existingLink != null) {
            existingLink.rels += link
        } else {
            dependencies += DependencyLink(child, link)
        }
    }
}
