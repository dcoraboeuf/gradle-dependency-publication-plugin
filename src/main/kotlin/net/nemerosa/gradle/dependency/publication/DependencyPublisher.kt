package net.nemerosa.gradle.dependency.publication

interface DependencyPublisher {

    /**
     * Publication
     *
     * @param node Root node to publish
     */
    fun publish(node: DependencyNode)

}