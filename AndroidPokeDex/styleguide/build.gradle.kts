dependencies {
    project(ModuleDependencies.shared)

    hiltImplementation()

    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.rxLibraries)
}