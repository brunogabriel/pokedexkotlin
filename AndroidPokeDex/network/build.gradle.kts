android {
    defaultConfig {
        buildConfigField("String", "API_URL", "\"https://pokeapi.co/api/v2/\"")
    }
}

dependencies {
    project(ModuleDependencies.shared)
    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.rxLibraries)
    implementation(AppDependencies.networkLibraries)
    implementation(AppDependencies.hiltLibraries)
    kapt(AppDependencies.hiltCompilers)

    debugApi (AppDependencies.debugChuckInterceptor)
    releaseApi (AppDependencies.releaseChuckInterceptor)
}