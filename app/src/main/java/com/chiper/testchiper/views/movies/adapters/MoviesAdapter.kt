package com.chiper.testchiper.views.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chiper.testchiper.Navigation
import com.chiper.testchiper.R
import com.chiper.testchiper.databinding.LayoutMovieItemBinding
import com.chiper.testchiper.models.Movie
import com.chiper.testchiper.views.movies.MoviesDetailFragment

class MoviesAdapter : RecyclerView.Adapter<MoviesHolder>() {
    private lateinit var layoutMovieItemBinding: LayoutMovieItemBinding
    private val movieList: ArrayList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        layoutMovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_movie_item, parent, false)
        return MoviesHolder(layoutMovieItemBinding).listen { pos, _ ->
            Navigation.attachFragment(MoviesDetailFragment.getInstance(movieList[pos]), R.id.fragment_container)
        }
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener { event.invoke(adapterPosition, itemViewType) }
        return this
    }

    fun setMovies(currentMovieList: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(currentMovieList)
        this.notifyDataSetChanged()
    }
}