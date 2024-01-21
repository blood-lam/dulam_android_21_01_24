package com.lamdx.smartmovie.ui.movie_detail.tabs.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.databinding.FragmentTabCastBinding
import com.lamdx.smartmovie.model.MovieDetails
import com.lamdx.smartmovie.ui.base.BaseListFragment
import com.lamdx.smartmovie.ui.movie_detail.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class TabCastFragment : BaseListFragment<FragmentTabCastBinding>() {

    private val columnCount = 3
    private var movieId: Int? = null

    private val viewModel by activityViewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val movieDetails = it.getParcelable("movieDetail", MovieDetails::class.java)
            movieId = movieDetails?.id
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab_cast_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                movieId?.let {
                    viewModel.viewModelScope.launch {
                        viewModel.movieCredit(it).collect { credit ->
                            adapter = MyTabCastRecyclerViewAdapter(credit.cast) {cast ->
                            }
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
    ): FragmentTabCastBinding {
        return FragmentTabCastBinding.inflate(inflater, container, false)
    }
}