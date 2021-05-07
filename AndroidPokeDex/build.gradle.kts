import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

buildScript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(AppDependencies.kotlin)
        classpath(AppDependencies.gradlePlugin)
        classpath(kotlin("gradle-plugin", Versions.kotlin))
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven("https://jipack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    configureAndroid()
}

fun Project.configureAndroid() {
    val isAppModule = name == "app"

    when {
        isAppModule -> configureAppAndroid()
        else -> return
    }

    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-android-extensions")
    apply(plugin = "kotlin-kapt")

    configure<BaseExtension> {
        compileSdkVersion(Config.compileSdk)
        buildToolsVersion(Config.buildToolsVersion)

        defaultConfig {
            minSdkVersion(Config.minSdk)
            targetSdkVersion(Config.targetSdk)
            versionCode = Config.versionCode
            versionName = Config.versionName
            testInstrumentationRunner = Config.androidTestInstrumentation
            vectorDrawables.userSupportLibrary = true
            multiDexEnabled = true
        }

        lintOptions {
            isCheckReleaseBuilds = false
            isCheckDependencies = true
            isCheckAllWarnings = true
            isWarningsAsErrors = true
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
            unitTests.isReturnDefaultValues = true
        }

        dataBinding.isEnabled = true

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
            getByName("test").java.srcDirs("src/test/kotlin")
            getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        }
    }

    configure<AndroidExtensionsExtension> {
        isExperimental = true
        defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
    }
}

fun Project.configureAppAndroid() {
    apply(plugin = "com.android.application")

    configure<BaseExtension> {
        signingConfigs {
            create("release") {
                isV2SigningEnabled = true
            }
        }

        defaultConfig {
            applicationId = Config.applicationId
        }
    }
}

fun Project.configureAndroidLibrary() {
    apply(plugin = "com.android.library")

    if (projectDir.name != "app") {
        tasks.register("cleanLibsModule") {
            dependsOn("clean")
        }
        tasks.register("buildLibsModule") {
            dependsOn("build")
        }
        tasks.register("publishLibsModule") {
            dependsOn("publish")
        }
    }
}