package com.chiper.testchiper.repository

import com.chiper.testchiper.App
import com.chiper.testchiper.R
import com.chiper.testchiper.api.MovieApi
import com.chiper.testchiper.models.Movie

class RepositoryManager(val movieApi: MovieApi) {
    suspend fun getMovies() : ArrayList<Movie>{
        return movieApi.getMovies(App.getAppContext().getString(R.string.app_api_key)).movies
    }
}