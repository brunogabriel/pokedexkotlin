dependencies {
    project(ModuleDependencies.shared)
    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.rxLibraries)
    implementation(AppDependencies.hiltLibraries)
    kapt(AppDependencies.hiltCompilers)
}