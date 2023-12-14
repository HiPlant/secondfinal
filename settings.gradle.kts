pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // SmoothBottomBar Library
        maven {
            url = uri("https://www.jitpack.io" )
        }
    }
}

rootProject.name = "My Applicationex1"
include(":app")
