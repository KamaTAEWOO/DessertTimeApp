pluginManagement {
    repositories {
        google()
//        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "DessertTimeApp"
include(":app")
include(":design")
include(":domain")
include(":feature")
include(":core")
include(":data")
include(":common:designsystem")
include(":common:navigation")
include(":common:utility")
include(":feature:auth")
include(":feature:home")
include(":feature:category")
include(":feature:mypage")
include(":feature:review")
include(":feature:like")
include(":feature:controler")
