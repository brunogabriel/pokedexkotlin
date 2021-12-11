import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dagger() {
    add("compileOnly", Dependencies.dagger)
    add("implementation", Dependencies.daggerAndroid)
    add("kapt", Dependencies.daggerCompiler)
    add("kapt", Dependencies.daggerAnnotationProcessor)
}

fun DependencyHandler.chuck() {
    add("debugImplementation", Dependencies.chuck)
    add("releaseImplementation", Dependencies.chuckNoOp)
}

fun DependencyHandler.okHttp() {
    add("implementation", platform(Dependencies.okHttpBom))
    add("implementation", Dependencies.okHttp)
    add("implementation", Dependencies.loggingInterceptor)
}

fun DependencyHandler.room() {
    add("implementation", Dependencies.room)
    add("kapt", Dependencies.roomAnnotationProcessor)
    add("implementation", Dependencies.roomKtx)

}