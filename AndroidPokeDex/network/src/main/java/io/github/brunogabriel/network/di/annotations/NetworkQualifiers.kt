package io.github.brunogabriel.network.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChuckInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlQualifier