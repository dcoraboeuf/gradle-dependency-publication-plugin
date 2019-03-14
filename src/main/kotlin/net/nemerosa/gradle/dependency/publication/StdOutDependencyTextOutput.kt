package net.nemerosa.gradle.dependency.publication

class StdOutDependencyTextOutput : DependencyTextOutput {
    override fun print(text: String) {
        println(text)
    }
}