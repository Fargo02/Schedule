package com.example.schedule.di

import com.example.schedule.domain.impl.ScheduleBetweenInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<ScheduleBetweenInteractorImpl> {
        ScheduleBetweenInteractorImpl(get())
    }
}