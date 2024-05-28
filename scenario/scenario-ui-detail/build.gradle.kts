apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/compose.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")
apply(from = "${project.rootDir}/gradle/unit_test.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    id("kotlin-parcelize")
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.ui.scenario.detail"

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // Implementations - Modules
    implementation(project(":common"))
    implementation(project(":mock-factory"))
    implementation(project(":scenario:scenario-domain-lib"))

    // Implementations - Libs
    implementation(libs.kotlinx.collections.immutable)

    // API - Modules
    api(project(":scenario:scenario-domain-api"))

    // Test Implementation - Modules
    testImplementation(project(":common-test"))
}
