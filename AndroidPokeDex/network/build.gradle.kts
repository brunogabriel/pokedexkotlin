android {
    defaultConfig {
        buildConfigField("String", "API_URL", "\"https://pokeapi.co/api/v2/\"")
    }
}

dependencies {
    project(ModuleDependencies.shared)

    hiltImplementation()

    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.rxLibraries)
    implementation(AppDependencies.networkLibraries)

    debugApi (AppDependencies.debugChuckInterceptor)
    releaseApi (AppDependencies.releaseChuckInterceptor)
}