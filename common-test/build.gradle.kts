apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.test.common"

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // Implementations - Unit Test
    implementation(libs.koin.junit5)
    implementation(libs.mockito.kotlin)
    implementation(libs.mockk)
    implementation(project(":app"))
}
