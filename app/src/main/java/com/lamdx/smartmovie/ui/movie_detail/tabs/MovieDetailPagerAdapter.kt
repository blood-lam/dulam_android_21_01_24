package com.lamdx.smartmovie.ui.movie_detail.tabs

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lamdx.smartmovie.model.MovieDetails

class MovieDetailPagerAdapter(
    private val listFragment: ArrayList<Fragment>,
    private val initData: MovieDetails,
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        val fragment = listFragment[position]
        fragment.arguments = bundleOf("movieDetail" to initData)
        return fragment
    }

    override fun getItemCount(): Int {
        return listFragment.size
    }
}