dependencies {
    project(
        listOf(
            ModuleDependencies.shared,
            ModuleDependencies.styleguide,
            ModuleDependencies.network,
            ModuleDependencies.database
        )
    )
    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.rxLibraries)
    implementationCompiler(AppDependencies.hiltLibraries, AppDependencies.hiltCompilers)
    implementation(AppDependencies.ktxLibraries)
}