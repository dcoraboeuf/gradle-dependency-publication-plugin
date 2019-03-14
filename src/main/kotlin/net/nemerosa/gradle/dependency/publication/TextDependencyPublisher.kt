package net.nemerosa.gradle.dependency.publication

class TextDependencyPublisher(
        val formatter: DependencyTextFormatter,
        val output: DependencyTextOutput
) : DependencyPublisher {
    override fun publish(node: DependencyNode) {
        val text = formatter.format(node)
        output.print(text)
    }
}
