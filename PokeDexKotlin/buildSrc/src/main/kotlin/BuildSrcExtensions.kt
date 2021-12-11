import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dagger() {
    add("compileOnly", Dependencies.dagger)
    add("implementation", Dependencies.daggerAndroid)
    add("kapt", Dependencies.daggerCompiler)
    add("kapt", Dependencies.daggerAnnotationProcessor)
}