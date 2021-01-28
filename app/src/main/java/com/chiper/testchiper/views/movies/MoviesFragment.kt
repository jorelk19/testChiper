package com.chiper.testchiper.views.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chiper.testchiper.Navigation
import com.chiper.testchiper.R
import com.chiper.testchiper.databinding.LayoutMoviesFragmentBinding
import com.chiper.testchiper.viewModels.MovieViewModel
import com.chiper.testchiper.views.movies.adapters.MoviesAdapter
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment(){
    private val movieVM : MovieViewModel by inject()
    private var moviesAdapter : MoviesAdapter? = null

    private lateinit var binding : LayoutMoviesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_movies_fragment, container, false)
        loadMovies()
        setAdapters()
        addSubscriptions()
        return binding.root
    }

    private fun setAdapters() {
        moviesAdapter = MoviesAdapter()
        binding.rvMovies.adapter = moviesAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(Navigation.getCurrentActivity())
    }

    private fun loadMovies() {
        movieVM.getMovies()
    }

    private fun addSubscriptions() {
        movieVM.getMoviesLiveData().observe(viewLifecycleOwner, Observer { movies ->
            moviesAdapter?.setMovies(movies)
        })
    }
}