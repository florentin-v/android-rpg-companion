pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "RPG Companion"

buildCache {
    local {
        isEnabled = true
        removeUnusedEntriesAfterDays = 30
    }
}

include(
    ":app",
    ":common",
    ":common-test",
    ":konsist-test",
    ":mock-factory",
)

apply(from = "character/settings.gradle.kts")
apply(from = "scenario/settings.gradle.kts")
apply(from = "session/settings.gradle.kts")
