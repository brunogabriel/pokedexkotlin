package io.github.brunogabriel.shared.rx

import io.reactivex.Scheduler

data class ApplicationSchedulers(
    val ioScheduler: Scheduler,
    val mainScheduler: Scheduler,
    val computationScheduler: Scheduler
)