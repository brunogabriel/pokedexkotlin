object AppDependencies {
    // defaults
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // android
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val material = "com.google.android.material:material:${Versions.material}"

    // hilt
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    private const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    val androidLibraries = listOf(kotlinStdLib, coreKtx, appcompat, material)

    // hilt
    val hiltLibraries = listOf(hilt)
    val hiltCompilers = listOf(hiltCompiler)
}