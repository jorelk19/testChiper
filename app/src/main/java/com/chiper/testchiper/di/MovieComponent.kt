package com.chiper.testchiper.di

import com.chiper.testchiper.repository.RepositoryManager
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieComponent : KoinComponent {
    private val repositoryManager : RepositoryManager by inject()
    val appComponent = AppComponent (
        repositoryManager = repositoryManager
    )
}

val appComponent by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MovieComponent().appComponent }