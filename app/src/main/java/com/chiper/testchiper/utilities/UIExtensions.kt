package com.chiper.testchiper.utilities

import android.widget.ImageView
import com.chiper.testchiper.App
import com.chiper.testchiper.BuildConfig
import com.chiper.testchiper.R
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

private val picasso: Picasso by lazy { Picasso.Builder(App.getAppContext()).downloader(OkHttp3Downloader(App.getAppContext())).build() }
fun ImageView.loadMovieImage(imageUrl: String) {
    picasso.load(BuildConfig.API_IMAGES + "/t/p/w500" + imageUrl)
        .placeholder(R.drawable.movie_image_placeholder)
        .into(this)
}
