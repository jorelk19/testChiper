package com.chiper.testchiper.di

import com.chiper.testchiper.api.MovieApi
import com.chiper.testchiper.repository.RepositoryManager
import org.koin.dsl.module

val repositoryModule = module {
    single { provideMovieRepository(get()) }
}

private fun provideMovieRepository(movieApi: MovieApi) = RepositoryManager(movieApi)

