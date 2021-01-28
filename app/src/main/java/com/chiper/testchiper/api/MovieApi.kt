package com.chiper.testchiper.api

import com.chiper.testchiper.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/4/list/1")
    suspend fun getMovies(@Query("api_key") apiKey: String): MovieResponse
}