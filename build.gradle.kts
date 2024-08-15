import java.io.ByteArrayOutputStream

// Top-level build file where you can add configuration options common to all sub-projects/modules.

apply(from = "${project.rootDir}/gradle/detekt.gradle")
apply(from = "${project.rootDir}/gradle/git_hooks.gradle.kts")
apply(from = "${project.rootDir}/gradle/ktlint.gradle")
apply(from = "${project.rootDir}/gradle/sonarqube.gradle")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.jlleitschuh.ktlint) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.sonarqube) apply false
}

buildscript {
    dependencies {
        classpath(libs.detekt.gradlePlugin)
    }
}

allprojects {
    apply(from = "${project.rootDir}/gradle/detekt_shared.gradle")
}

tasks.register("konsistCheck") {
    group = "verification"
    description = "Runs Konsist static code analysis"

    doLast {
        val output = ByteArrayOutputStream()
        val result = project.exec {
            commandLine("./gradlew", "konsist-test:test", "--rerun-tasks")
            standardOutput = output
            errorOutput = output
            isIgnoreExitValue = true
        }

        println(output.toString())

        if (result.exitValue != 0) {
            throw GradleException("Konsist tests failed")
        }
    }
}
