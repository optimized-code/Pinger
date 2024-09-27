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

rootProject.name = "Pingo"
include(":app")
include(":features:home:domain")
include(":features:home:data")
include(":features:home:ui")
include(":features:add_links:domain")
include(":features:add_links:data")
include(":features:add_links:ui")
include(":features:logs:domain")
include(":features:logs:data")
include(":features:logs:ui")
include(":core:common")
include(":core:network")
include(":core:navigation")
include(":core:permissions")
