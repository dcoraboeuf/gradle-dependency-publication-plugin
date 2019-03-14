package net.nemerosa.gradle.dependency.publication

class StringDependencyTextOutput : DependencyTextOutput {

    var value: String? = null

    override fun print(text: String) {
        value = text
    }
}