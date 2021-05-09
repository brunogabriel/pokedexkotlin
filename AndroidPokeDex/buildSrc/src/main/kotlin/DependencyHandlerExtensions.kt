import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.project(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", project(mapOf("path" to dependency)))
    }
}

fun DependencyHandler.project(module: String) {
    add("implementation", project(mapOf("path" to module)))
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementationCompiler(libraries: List<String>, compilers: List<String>) {
    libraries.forEach {
        add("implementation", it)
    }

    compilers.forEach {
        add("kapt", it)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}