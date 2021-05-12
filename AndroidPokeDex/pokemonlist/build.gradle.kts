dependencies {
    project(
        listOf(
            ModuleDependencies.shared,
            ModuleDependencies.styleguide,
            ModuleDependencies.network,
            ModuleDependencies.database
        )
    )

    hiltImplementation()

    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.rxLibraries)
    implementation(AppDependencies.ktxLibraries)
}