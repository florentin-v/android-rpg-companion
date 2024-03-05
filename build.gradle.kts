// Top-level build file where you can add configuration options common to all sub-projects/modules.

apply(from = "${project.rootDir}/gradle/ktlint.gradle")
apply(from = "${project.rootDir}/gradle/sonarqube.gradle")
apply(from = "${project.rootDir}/gradle/detekt.gradle")

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
