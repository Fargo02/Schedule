package com.example.schedule.di

import com.example.schedule.domain.api.ScheduleInteractor
import com.example.schedule.domain.db.StationCodeInteractor
import com.example.schedule.domain.impl.ScheduleInteractorImpl
import com.example.schedule.domain.impl.StationCodeInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<ScheduleInteractor> {
        ScheduleInteractorImpl(get())
    }

    single <StationCodeInteractor> {
        StationCodeInteractorImpl(get())
    }
}