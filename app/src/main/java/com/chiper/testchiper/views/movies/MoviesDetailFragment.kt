package com.chiper.testchiper.views.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chiper.testchiper.R
import com.chiper.testchiper.databinding.LayoutMovieDetailFragmentBinding
import com.chiper.testchiper.models.Movie
import com.chiper.testchiper.utilities.loadMovieImage

class MoviesDetailFragment : Fragment() {

    private lateinit var binding: LayoutMovieDetailFragmentBinding
    private var movie: Movie? = null

    companion object {
        fun getInstance(currentMovie: Movie) : MoviesDetailFragment {
            val instance = MoviesDetailFragment()
            instance.setCurrentMovie(currentMovie)
            return instance
        }
    }

    private fun setCurrentMovie(currentMovie: Movie) {
        movie = currentMovie
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_movie_detail_fragment, container, false)
        loadMovieData()
        return binding.root
    }

    private fun loadMovieData() {
        movie?.let { currentMovie ->
            currentMovie.posterPath?.let {
                binding.ivMovie.loadMovieImage(it)
            }
            binding.tvMovieName.text = currentMovie.title
            binding.tvMovieOverview.text = currentMovie.overview
            binding.tvMovieDate.text = currentMovie.releaseDate
        }
    }
}