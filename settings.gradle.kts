pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DessertTimeApp"
include(":app")
include(":design")
include(":domain")
include(":feature")
include(":core")
include(":data")
include(":common")
include(":common:designsystem")
include(":common:navigation")
include(":common:utility")
include(":feature:auth")
include(":feature:gome")
include(":feature:home")
