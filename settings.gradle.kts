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

rootProject.name = "Popular Movies"
include(":app")
include(":core")
include(":design-system")
include(":feature_movies")
include(":feature_movies:presentation")
include(":feature_movies:data")
include(":feature_movies:domain")
include(":core-domain")
include(":feature_movie_details")
include(":feature_movie_details:presentation")
include(":feature_movie_details:data")
include(":feature_movie_details:domain")
