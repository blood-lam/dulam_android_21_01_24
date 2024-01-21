package com.lamdx.smartmovie.ui.discover.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lamdx.smartmovie.MainViewModel
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.databinding.FragmentMovieListBinding
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.ui.base.BaseListFragment

/**
 * A fragment representing a list of Items.
 */
class MovieListFragment : BaseListFragment<FragmentMovieListBinding>() {

    private var columnCount = 2

    private val mainViewMode: MainViewModel by activityViewModels<MainViewModel>()

    var movieArray: List<Movie>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        mainViewMode.targetViewModel.observe(viewLifecycleOwner) {
            columnCount = if (it == "Detail Mode") 2 else 1
            setAdapter(view)
        }
        return view
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentMovieListBinding {
        return FragmentMovieListBinding.inflate(inflater, container, false)
    }

    fun setAdapter(_view: View? = null) {
        var view = _view
        if (view == null) {
            view = getView()
        }
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
//                val movieArray =
//                    arguments?.getParcelableArrayList("movies", Movie::class.java)
                adapter = movieArray?.let {
                    val adapter = MyMovieListRecyclerViewAdapter(columnCount) {movie ->
                        callback?.onShowMovieDetail(movie.id)
                    }
                    adapter.setData(it)
                }
            }
        }
    }

}