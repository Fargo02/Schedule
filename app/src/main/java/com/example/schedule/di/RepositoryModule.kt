package com.example.schedule.di

import com.example.schedule.data.search.ScheduleRepositoryImpl
import com.example.schedule.data.search.StationCodeRepositoryImpl
import com.example.schedule.domain.api.ScheduleRepository
import com.example.schedule.domain.db.StationCodeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<ScheduleRepository> {
        ScheduleRepositoryImpl(get(), get(), get())
    }

    single<StationCodeRepository> {
        StationCodeRepositoryImpl(get(), get())
    }
}