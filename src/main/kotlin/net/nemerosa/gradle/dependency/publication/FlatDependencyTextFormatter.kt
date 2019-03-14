package net.nemerosa.gradle.dependency.publication

import java.io.StringWriter

class FlatDependencyTextFormatter(
        val levels: Int = 0,
        val groupSeparator: String = ":",
        val prefix: String = ""
) : DependencyTextFormatter {

    override fun format(node: DependencyNode): String {
        val buffer = StringWriter()
        formatNode(node, buffer, 0)
        return buffer.toString()
    }

    private fun formatNode(node: DependencyNode, buffer: StringWriter, level: Int) {
        node.dependencies.forEach { link ->
            format(link.node, buffer, level)
        }
    }

    private fun format(node: DependencyNode, buffer: StringWriter, level: Int) {
        if (level <= levels) {
            buffer.write(
                    "$prefix${node.group}$groupSeparator${node.name} = ${node.version}\n"
            )
            formatNode(node, buffer, level + 1)
        }
    }

}