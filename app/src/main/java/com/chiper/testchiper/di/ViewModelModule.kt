package com.chiper.testchiper.di

import com.chiper.testchiper.dialogs.SnackFactory
import com.chiper.testchiper.viewModels.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(appComponent, SnackFactory) }
}