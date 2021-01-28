package com.chiper.testchiper.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movies : ArrayList<Movie> = arrayListOf()
)
