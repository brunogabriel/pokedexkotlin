object AppDependencies {
    // defaults
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // android
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // hilt
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // rx
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxAdapter}"
    const val rxAdapterJava = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxAdapterJava}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"

    // ui
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val palette = "androidx.palette:palette-ktx:${Versions.palette}"

    // chuck
    const val debugChuckInterceptor = "com.readystatesoftware.chuck:library:${Versions.chuckInterceptor}"
    const val releaseChuckInterceptor = "com.readystatesoftware.chuck:library-no-op:${Versions.chuckInterceptor}"

    // room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomRx = "androidx.room:room-rxjava2:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // other ktx
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"

    // libraries

    // android
    val androidLibraries = listOf(kotlinStdLib, coreKtx, appcompat, material)

    // rx
    val rxLibraries = listOf(rxAndroid, rxKotlin, rxJava)

    // network libraries
    val networkLibraries = listOf(retrofit, rxAdapter, loggingInterceptor, rxAdapterJava, gsonConverter)

    // ui libraries
    val uiLibraries = listOf(picasso, palette)

    // room
    val roomLibraries = listOf(room, roomRx)
    val roomCompilers = listOf(roomCompiler)

    // other ktx
    val ktxLibraries = listOf(fragmentKtx, lifecycleKtx)
}