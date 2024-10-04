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

rootProject.name = "mod3layoutCompose"
include(":app")
include(":mod3correction")
include(":mod4demoremember")
include(":mod4demoviewmodel")
include(":mod4corrviewmodel")
include(":mod4demonavigationwithstate")
include(":mod5demointents")
include(":mod5demotestscompose")
include(":mod5demonavigation")
include(":filrouge")
include(":mod6demosqllite")
include(":mod6demodatastore")
include(":mod6corrdatastore")
