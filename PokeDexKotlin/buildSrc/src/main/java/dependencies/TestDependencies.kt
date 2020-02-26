package dependencies

/**
 * Created by bruno on 2020-02-26
 */
object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ARCH_TESTING = "androidx.arch.core:core-testing:${Versions.ARCH_COMPONENTS}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
    const val KOIN_TEST = "org.koin:koin-test:${Versions.KOIN}"
    const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_KOTLIN}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO_CORE}"
    const val ESPRESSO_RUNNER = "androidx.test:runner:${Versions.ESPRESSO_RUNNER}"
    const val ESPRESSO_RULE = "androidx.test:rules:${Versions.ESPRESSO_RULE}"
    const val ORCHESTRATOR = "androidx.test:orchestrator:${Versions.ESPRESSO_RUNNER}"
    const val WIREMOCK = "com.github.tomakehurst:wiremock-standalone:${Versions.WIREMOCK}"
}