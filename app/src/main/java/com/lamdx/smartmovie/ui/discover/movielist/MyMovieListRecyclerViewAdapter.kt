package com.lamdx.smartmovie.ui.discover.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.utils.Constant
import com.lamdx.smartmovie.utils.MovieDiffUtilCallback
import com.lamdx.smartmovie.utils.PosterSizes
import com.lamdx.smartmovie.utils.imagePresent


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieListRecyclerViewAdapter(
    private val layoutMode: Int,
    private val onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MyMovieListRecyclerViewAdapter.MovieViewHolder>() {

    private var values: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
                if (layoutMode == 1) R.layout.fragment_movie_detail_mode else R.layout.fragment_movie_icon_mode,
                parent,
                false
            )

        return MovieViewHolder(
            view, onClick
        )

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = values[position]
        holder.movieTitle.text = movie.title
        holder.movieReleaseDate.text = movie.releaseDate
        holder.movieOverview?.let {
            it.text = movie.overview
        }
        holder.bind(movie)
    }

    fun setData(values: List<Movie>): MyMovieListRecyclerViewAdapter {
        this.values = values.toMutableList()
        return this
    }

    override fun getItemCount(): Int = values.size

    private fun updateData(newValues: List<Movie>) {
        val diffCallback = MovieDiffUtilCallback(values, newValues)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        values.clear()
        values.addAll(newValues)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MovieViewHolder(itemView: View, val onClick: (Movie) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val movieImageView: ImageView = itemView.findViewById(R.id.movie_poster)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieReleaseDate: TextView = itemView.findViewById(R.id.movie_release_date)
        val movieOverview: TextView? = itemView.findViewById(R.id.movie_overview)
        private var currentMovie: Movie? = null

        init {
            itemView.setOnClickListener {
                currentMovie?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(movie: Movie) {
            currentMovie = movie
            val imgUrl =
                "${Constant.IMG_HOST}/${PosterSizes.original.toString() + movie.posterPath}"
            imagePresent(itemView, imgUrl, movieImageView)

        }
    }
}
