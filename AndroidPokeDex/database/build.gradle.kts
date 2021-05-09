dependencies {
    implementation(AppDependencies.androidLibraries)
    implementation(AppDependencies.uiLibraries)
    implementation(AppDependencies.rxLibraries)
    implementationCompiler(AppDependencies.hiltLibraries, AppDependencies.hiltCompilers)
    implementationCompiler(AppDependencies.roomLibraries, AppDependencies.roomCompilers)
}