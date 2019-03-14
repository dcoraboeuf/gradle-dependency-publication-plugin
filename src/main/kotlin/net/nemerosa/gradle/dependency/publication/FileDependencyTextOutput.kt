package net.nemerosa.gradle.dependency.publication

import java.io.File

class FileDependencyTextOutput(
        val file: File
) : DependencyTextOutput {
    override fun print(text: String) {
        file.writeText(text)
    }
}
