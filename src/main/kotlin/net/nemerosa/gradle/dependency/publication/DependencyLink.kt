package net.nemerosa.gradle.dependency.publication

data class DependencyLink(
        val node: DependencyNode,
        val rels: MutableList<String> = mutableListOf()
) {
    constructor(node: DependencyNode, rel: String) : this(
            node,
            mutableListOf(rel)
    )
}
