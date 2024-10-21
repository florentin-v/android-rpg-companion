apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/compose.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    id("kotlin-parcelize")
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.ui.character.detail"
}

dependencies {
    // Implementations - Modules
    implementation(project(":common"))
    implementation(project(":mock-factory"))
    implementation(project(":character:character-domain-lib"))

    // API - Modules
    api(project(":character:character-domain-api"))
}