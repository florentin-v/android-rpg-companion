apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")
apply(from = "${project.rootDir}/gradle/room.gradle")
apply(from = "${project.rootDir}/gradle/unit_test.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    id("kotlin-parcelize")
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion.data.character"

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // Implementations - Modules
    implementation(project(":common"))

    // Implementations - Google
    implementation(libs.google.api.services.docs)
    implementation(libs.google.auth.library.oauth2.http)

    // Implementations - Okhttp3
    implementation(libs.okhttp3.logging)
    implementation(libs.okhttp3.okhttp)

    // Implementations - Retrofit2
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    implementation(libs.retrofit2.retrofit)

    // API - Modules
    api(project(":character:character-domain-api"))

    // Test Implementations - Modules
    testImplementation(project(":common-test"))
}
