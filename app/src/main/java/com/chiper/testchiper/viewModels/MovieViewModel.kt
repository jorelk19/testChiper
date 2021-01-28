package com.chiper.testchiper.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chiper.testchiper.Navigation
import com.chiper.testchiper.R
import com.chiper.testchiper.di.AppComponent
import com.chiper.testchiper.dialogs.SnackFactory
import com.chiper.testchiper.models.Movie
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieViewModel(private val appComponent: AppComponent, val snackFactory: SnackFactory) : ViewModelBase() {
    var movies = MutableLiveData<ArrayList<Movie>>()
    fun getMoviesLiveData(): LiveData<ArrayList<Movie>> {
        return movies
    }

    fun getMovies() {
        launch(Dispatchers.Main) {
            try {
                Navigation.showLoader()
                val movies = withContext(Dispatchers.IO) { appComponent.repositoryManager.getMovies() }
                setMovies(movies)
                Navigation.hideLoader()
            } catch (ex: HttpException) {
                Navigation.hideLoader()
                snackFactory.showErrorMessageWithTiming(Navigation.getCurrentActivity(), R.id.coordinator_main_activity, Navigation.getCurrentActivity().getString(R.string.something_went_wrong_retry), snackFactory.FIVE_SECONDS)
            }
        }
    }

    private fun setMovies(currentMovies: ArrayList<Movie>) {
        movies.value = currentMovies
    }
}
