apply(from = "${project.rootDir}/gradle/android_shared.gradle")
apply(from = "${project.rootDir}/gradle/compose.gradle")
apply(from = "${project.rootDir}/gradle/koin.gradle")
apply(from = "${project.rootDir}/gradle/unit_test.gradle")
apply(from = "${project.rootDir}/gradle/kover.gradle")
apply(from = "${project.rootDir}/gradle/project_dependency_graph.gradle")
apply(plugin = "kotlin-parcelize")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
}

android {
    namespace = "com.fvanaldewereld.rpgcompanion"

    defaultConfig {
        applicationId = "com.fvanaldewereld.rpgcompanion"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    // Implementations - Modules
    implementation(project(":common"))
    implementation(project(":mock-factory"))

    implementation(project(":character:character-data"))
    implementation(project(":character:character-domain-lib"))
    implementation(project(":character:character-ui-detail"))
    implementation(project(":character:character-ui-list"))
    
    implementation(project(":game:game-data"))
    implementation(project(":game:game-domain-lib"))
    implementation(project(":game:game-ui-detail"))
    implementation(project(":game:game-ui-list"))

    implementation(project(":scenario:scenario-data"))
    implementation(project(":scenario:scenario-domain-lib"))
    implementation(project(":scenario:scenario-ui-detail"))
    implementation(project(":scenario:scenario-ui-list"))

    implementation(project(":session:session-data"))
    implementation(project(":session:session-domain-lib"))
    implementation(project(":session:session-ui-detail"))
    implementation(project(":session:session-ui-list"))

    // Implementations - Libraries
    implementation(libs.kotlinx.collections.immutable)
}
