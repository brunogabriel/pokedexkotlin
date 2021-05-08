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

    // rx
    private const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    private const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    private const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"

    // retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxAdapter}"
    private const val rxAdapterJava =
        "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxAdapterJava}"
    private const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    private const val gsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"

    // ui
    private const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    private const val palette = "androidx.palette:palette-ktx:${Versions.palette}"

    // chuck
    const val debugChuckInterceptor = "com.readystatesoftware.chuck:library:${Versions.chuckInterceptor}"
    const val releaseChuckInterceptor = "com.readystatesoftware.chuck:library-no-op:${Versions.chuckInterceptor}"

    /**
     * Libraries
     */

    // android
    val androidLibraries = listOf(kotlinStdLib, coreKtx, appcompat, material)

    // hilt
    val hiltLibraries = listOf(hilt)
    val hiltCompilers = listOf(hiltCompiler)

    // rx
    val rxLibraries = listOf(rxAndroid, rxKotlin, rxJava)

    // network libraries
    val networkLibraries =
        listOf(retrofit, rxAdapter, loggingInterceptor, rxAdapterJava, gsonConverter)

    // ui libraries
    val uiLibraries = listOf(picasso, palette)
}