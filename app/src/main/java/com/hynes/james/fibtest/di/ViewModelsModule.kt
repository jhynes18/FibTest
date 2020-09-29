package com.hynes.james.fibtest.di

import com.hynes.james.fibtest.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        MainViewModel(androidApplication())
    }
}