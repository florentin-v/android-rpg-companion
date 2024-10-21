apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")
apply(from = "${project.rootDir}/gradle/unit_test.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    id("kotlin-parcelize")
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.lib.domain.game"

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // API - Modules
    api(project(":game:game-domain-api"))

    // Test Implementations - Modules
    testImplementation(project(":common-test"))
}