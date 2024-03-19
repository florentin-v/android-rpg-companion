apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")
apply(from = "${project.rootDir}/gradle/ui_test.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.common.test"

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // Implementations - Modules
    implementation(project(":app"))
    implementation(project(":common"))

    // Implementations - Libs - Unit Tests
    implementation(libs.koin.junit5)
    implementation(libs.mockito.kotlin)
    implementation(libs.mockk)

    // Implementations - Libs - UI Tests
    implementation(libs.androidx.compose.ui.test.junit4)
    implementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.navigation.testing)
}
