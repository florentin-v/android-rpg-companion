// Top-level build file where you can add configuration options common to all sub-projects/modules.

apply(from = "${project.rootDir}/gradle/ktlint.gradle")
apply(from = "${project.rootDir}/gradle/sonarqube.gradle")
apply(from = "${project.rootDir}/gradle/kover.gradle")
apply(from = "${project.rootDir}/gradle/detekt.gradle")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.jlleitschuh.ktlint) apply false
    alias(libs.plugins.module.dependency.graph)
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.sonarqube) apply false
    alias(libs.plugins.ksp) apply false
}

buildscript {
    dependencies {
        classpath(libs.jetbrains.kotlinx.kover)
        classpath(libs.detekt.gradlePlugin)
    }
}

allprojects {
    apply(plugin = "kover")
    apply(from = "${project.rootDir}/gradle/detekt_shared.gradle")
}
