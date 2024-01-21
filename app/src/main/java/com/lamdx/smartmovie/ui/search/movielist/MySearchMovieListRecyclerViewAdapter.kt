package com.lamdx.smartmovie.ui.search.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.model.Genres
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.model.SearchMovie
import com.lamdx.smartmovie.utils.Constant
import com.lamdx.smartmovie.utils.PosterSizes
import com.lamdx.smartmovie.utils.imagePresent

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MySearchMovieListRecyclerViewAdapter(
    private val values: List<SearchMovie>,
    private val onClick: (SearchMovie) -> Unit,
) : RecyclerView.Adapter<MySearchMovieListRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_search_movie, parent, false)

        return MovieViewHolder(
            view,
            onClick
        )

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = values[position]
        holder.title.text = movie.title
        holder.genres.text = movie.genres.map { it?.name }.reduce { acc, genres -> "$acc | $genres" }
        holder.rating.rating = movie.voteAverage / 2f
        holder.bind(movie)
    }

    override fun getItemCount(): Int = values.size

    inner class MovieViewHolder(itemView: View, val onClick: (SearchMovie) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.search_movie_title)
        val genres: TextView = itemView.findViewById(R.id.search_movie_genres)
        val rating: RatingBar = itemView.findViewById(R.id.movie_rating)
        private val movieImageView: ImageView = itemView.findViewById(R.id.search_movie_banner)

        private var currentMovie: SearchMovie? = null

        init {
            itemView.setOnClickListener {
                currentMovie?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(movie: SearchMovie) {
            currentMovie = movie
            val imgUrl =
                "${Constant.IMG_HOST}/${PosterSizes.original.toString() + movie.backdropPath}"
            imagePresent(itemView, imgUrl, movieImageView)

        }
    }

}