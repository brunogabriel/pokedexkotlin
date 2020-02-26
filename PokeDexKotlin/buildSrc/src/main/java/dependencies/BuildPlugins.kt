package dependencies

/**
 * Created by bruno on 2020-02-26
 */
object BuildPlugins {
    const val GRADLE = "com.android.tools.build:gradle:${PluginsVersions.GRADLE}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}"
    const val JACOCO = "org.jacoco:org.jacoco.core:${PluginsVersions.JACOCO}"
}