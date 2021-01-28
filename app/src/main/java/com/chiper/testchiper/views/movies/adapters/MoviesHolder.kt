package com.chiper.testchiper.views.movies.adapters

import androidx.recyclerview.widget.RecyclerView
import com.chiper.testchiper.databinding.LayoutMovieItemBinding
import com.chiper.testchiper.models.Movie
import com.chiper.testchiper.utilities.loadMovieImage

class MoviesHolder(private val layoutMovieItemBinding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(layoutMovieItemBinding.root) {
    fun bind(currentMovie: Movie) {
        currentMovie.posterPath?.let {
            layoutMovieItemBinding.ivMovie.loadMovieImage(it)
        }
        layoutMovieItemBinding.tvMovieName.text = currentMovie.title
    }
}