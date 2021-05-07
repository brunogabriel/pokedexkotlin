repositories {
    google()  // For the Android support libraries.
}

android {
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    applicationVariants.all {
        outputs.all {
            if (name.contains("release"))
                (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                    "sample-app-release.apk"
        }
    }

    signingConfigs {
        getByName("release") {
            isV2SigningEnabled = true
            keyAlias = "a"
            storeFile = rootProject.file("./keystore/a")
            keyPassword = "123456"
            storePassword = "123456"
        }
    }
}

dependencies {
    implementation(AppDependencies.androidLibraries)
}

