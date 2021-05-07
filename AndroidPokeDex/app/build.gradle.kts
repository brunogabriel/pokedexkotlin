repositories {
    google()
}

android {
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    applicationVariants.all {
        outputs.all {
            if (name.contains("release"))
                (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                    "app-release.apk"
        }
    }

    signingConfigs {
        getByName("release") {
           // TODO:
        }
    }
}

dependencies {
    implementation(AppDependencies.androidLibraries)
}
