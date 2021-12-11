object Dependencies {
    // Android
    const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    // UI
    const val material = "com.google.android.material:material:${Versions.material}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAnnotationProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // Serialization
    const val kotlinxSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val kotlinxSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinxSerializationConverter}"
    const val chuck = "com.github.chuckerteam.chucker:library:${Versions.chuck}"
    const val chuckNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chuck}"
    const val okHttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.okHttp}"
    const val okHttp = "com.squareup.okhttp3:okhttp"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"

    // Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // database
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomAnnotationProcessor = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}