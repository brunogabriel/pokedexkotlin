package dependencies

/**
 * Created by bruno on 27/02/20
 */
object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.KOTLIN}"
    const val KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val KOIN = "org.koin:koin-android:${Versions.KOIN}"
    const val ARCH_COMPONENTS =
        "androidx.lifecycle:lifecycle-extensions:${Versions.ARCH_COMPONENTS}"
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.DETEKT}"
    const val KTLINT = "com.pinterest:ktlint:${Versions.KTLINT}"

    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    }

    object Koin {
        const val CORE = "org.koin:koin-android:${Versions.KOIN}"
        const val SCOPE = "org.koin:koin-androidx-scope:${Versions.KOIN}"
        const val VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_RX_ADAPTER =
            "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}"
        const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    }

    object Database {
        const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_RX = "androidx.room:room-rxjava2:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    }

    object RX {
        const val RX_JAVA = "io.reactivex.rxjava2:rxjava:${Versions.RX_JAVA}"
        const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.RX_KOTLIN}"
        const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    }
}

