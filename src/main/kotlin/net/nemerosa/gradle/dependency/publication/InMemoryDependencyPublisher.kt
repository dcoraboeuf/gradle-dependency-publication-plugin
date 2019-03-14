package net.nemerosa.gradle.dependency.publication

class InMemoryDependencyPublisher : DependencyPublisher {

    override fun publish(node: DependencyNode) {
        this.node = node
    }

    var node: DependencyNode? = null

}