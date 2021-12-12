plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    compileSdk = AppConfig.compileSdk

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.shared))
    implementation(project(Modules.network))

    // dependencies
    dagger()
    okHttp()
    room()
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.material)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.kotlinxSerialization)
    implementation(Dependencies.coroutines)

    // test dependencies
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.androidxJunit)
    androidTestImplementation(TestDependencies.espresso)
}