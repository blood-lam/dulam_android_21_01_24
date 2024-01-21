package com.lamdx.smartmovie.ui.discover.tabs.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.ui.discover.DiscoverViewModel
import com.lamdx.smartmovie.ui.discover.movielist.MovieListFragment
import com.lamdx.smartmovie.utils.ApiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [TabMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class TabMoviesFragment : Fragment() {

    private var movies: List<Movie>? = emptyList()

    private val discoverViewModel by activityViewModels<DiscoverViewModel>()

    private var movieListFragment: MovieListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movies = it.getParcelableArrayList("fromDiscover", Movie::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab_movies, container, false)
        movieListFragment = childFragmentManager.findFragmentById(R.id.tab_movie_list) as MovieListFragment
        movieListFragment?.movieArray = movies

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.fresh_tab_movie)
        swipeRefreshLayout?.let {
            it.setOnRefreshListener {
                refreshData(it)
            }
        }
        return view
    }

    private fun refreshData(swipeRefreshLayout: SwipeRefreshLayout) {
        discoverViewModel.viewModelScope.launch {
            discoverViewModel.discoverState.collect { state ->
                when (state.status) {
                    ApiStatus.LOADING -> swipeRefreshLayout.isRefreshing = true
                    ApiStatus.SUCCESS -> {
                        swipeRefreshLayout.isRefreshing = false
                        val data: List<List<Movie>>? = state.data
                        if (data.isNullOrEmpty()) {
                            return@collect
                        } else {
                            movieListFragment?.let {
                                it.movieArray = data[0]
                                movieListFragment?.setAdapter()
                            }
                        }
                    }

                    ApiStatus.ERROR -> println(state.message)
                }
            }
        }
    }
}