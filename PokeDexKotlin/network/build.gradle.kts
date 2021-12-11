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

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.kotlinxSerialization)
    implementation(Dependencies.kotlinxSerializationConverter)
    dagger()
    chuck()
    okHttp()

    testImplementation(TestDependencies.junit)
}