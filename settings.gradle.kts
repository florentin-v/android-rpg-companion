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
    ":scenario:scenario-data",
    ":scenario:scenario-domain-api",
    ":scenario:scenario-domain-lib",
    ":scenario:scenario-ui-detail",
    ":scenario:scenario-ui-list",
)
