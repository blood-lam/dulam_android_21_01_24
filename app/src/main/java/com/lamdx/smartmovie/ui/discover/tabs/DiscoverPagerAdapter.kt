package com.lamdx.smartmovie.ui.discover.tabs

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lamdx.smartmovie.model.Movie


class DiscoverPagerAdapter(
    private val listFragment: ArrayList<Fragment>,
    private val initData: List<List<Movie>>,
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        val fragment = listFragment[position]
        println(initData[position].size)
        fragment.arguments = bundleOf("fromDiscover" to initData[position])
        return fragment
    }

    override fun getItemCount(): Int {
        return listFragment.size
    }
}