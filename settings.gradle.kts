dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Axie Controller"
include (":app",":AxieInfinityApi",":CoinMarketApi")
//include(":AxieInfinityApi")
//include(":CoinMarketApi")
