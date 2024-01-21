package com.lamdx.smartmovie.ui.search.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lamdx.smartmovie.MainViewModel
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.databinding.FragmentSearchMovieListBinding
import com.lamdx.smartmovie.model.Genres
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.model.SearchMovie
import com.lamdx.smartmovie.ui.base.BaseListFragment
import com.lamdx.smartmovie.ui.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class SearchMovieListFragment : BaseListFragment<FragmentSearchMovieListBinding>() {

    private val searchViewModel by activityViewModels<SearchViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    private val columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_movie_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                searchViewModel.viewModelScope.launch {
                    combine(
                        searchViewModel.searchedMovie,
                        mainViewModel.genresFlow
                    ) { movies, genres ->
                        movies
                            .map { movie ->
                            val gens = movie.genreIds.map { genres?.find { gen: Genres -> gen.id == it } }
                            SearchMovie(
                                movie.backdropPath,
                                gens,
                                movie.id,
                                movie.title,
                                movie.voteAverage
                            )
                        }
                    }.flowOn(Dispatchers.IO).collect {
                        adapter =
                            MySearchMovieListRecyclerViewAdapter(it) { movie ->
                                callback?.onShowMovieDetail(movie.id)
                            }
                    }
                }
            }
        }
        return view
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentSearchMovieListBinding {
        return FragmentSearchMovieListBinding.inflate(inflater, container, false)
    }

}