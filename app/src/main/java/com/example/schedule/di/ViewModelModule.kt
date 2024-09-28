package com.example.schedule.di

import com.example.schedule.ui.home.view_model.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(get())
    }

}