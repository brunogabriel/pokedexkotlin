object AppDependencies {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // android
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val material = "com.google.android.material:material:${Versions.material}"

    val androidLibraries = listOf(kotlinStdLib, coreKtx, appcompat, material)
}