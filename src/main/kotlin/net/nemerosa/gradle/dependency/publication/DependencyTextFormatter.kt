package net.nemerosa.gradle.dependency.publication

interface DependencyTextFormatter {

    fun format(node: DependencyNode): String

}