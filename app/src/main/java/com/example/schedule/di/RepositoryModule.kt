package com.example.schedule.di

import com.example.schedule.data.search.ScheduleBetweenRepositoryImpl
import com.example.schedule.domain.api.ScheduleBetweenRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<ScheduleBetweenRepository> {
        ScheduleBetweenRepositoryImpl(get())
    }

}