apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")
apply(from = "${project.rootDir}/gradle/unit_test.gradle")


plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.test.konsist"

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // Test Implementations
    implementation(libs.konsist)
}
