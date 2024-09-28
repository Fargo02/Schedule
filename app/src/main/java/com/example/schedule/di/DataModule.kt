package com.example.schedule.di

import com.example.schedule.data.search.NetworkClient
import com.example.schedule.data.search.network.RetrofitNetworkClient
import com.example.schedule.data.search.network.ScheduleApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<ScheduleApi> {
        Retrofit.Builder()
            .baseUrl("https://api.rasp.yandex.net/v3.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ScheduleApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get())
    }

}