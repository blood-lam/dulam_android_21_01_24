package com.lamdx.smartmovie.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.databinding.FragmentDiscoverBinding
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.ui.discover.tabs.DiscoverPagerAdapter
import com.lamdx.smartmovie.ui.discover.tabs.movies.TabMoviesFragment
import com.lamdx.smartmovie.ui.discover.tabs.now_playing.TabNowPlayingFragment
import com.lamdx.smartmovie.ui.discover.tabs.popular.TabPopularFragment
import com.lamdx.smartmovie.ui.discover.tabs.top_rate.TabTopRateFragment
import com.lamdx.smartmovie.ui.discover.tabs.up_coming.TabUpComingFragment
import com.lamdx.smartmovie.utils.ApiResult
import com.lamdx.smartmovie.utils.ApiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null

    private val discoverViewModel by activityViewModels<DiscoverViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val toolbar = activity?.findViewById<Toolbar>(R.id.cus_toolbar)
        toolbar?.let {
            it.menu?.findItem(R.id.toolbar_view_mode)?.setVisible(true)
            it.menu?.findItem(R.id.toolbar_search_symbol)?.setVisible(false)
        }

        val view = inflater.inflate(R.layout.fragment_discover, container, false)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs)

        initTabLayout(discoverViewModel.discoverState, viewPager, tabLayout)

        return view
    }

    private fun initTabLayout(
        discoverState: StateFlow<ApiResult<List<List<Movie>>>>,
        viewPager: ViewPager2,
        tabLayout: TabLayout,
    ) {
        lifecycleScope.launch {
            discoverState.collect { state ->
                when (state.status) {
                    ApiStatus.LOADING -> println("Loading")
                    ApiStatus.SUCCESS -> {
                        val data = state.data ?: emptyList()
                        setupViewPager(viewPager, data)
                        setTabLayout(tabLayout, viewPager)
                    }

                    ApiStatus.ERROR -> println(state.message)
                }
            }
        }
    }

    private fun setupViewPager(viewPager2: ViewPager2, initData: List<List<Movie>>) {
        val tabFragments = arrayListOf(
            TabMoviesFragment(),
            TabPopularFragment(),
            TabTopRateFragment(),
            TabUpComingFragment(),
            TabNowPlayingFragment(),
        )
        viewPager2.adapter =
            DiscoverPagerAdapter(tabFragments, initData, childFragmentManager, lifecycle)
    }

    private fun setTabLayout(tabLayout: TabLayout, viewPager2: ViewPager2) {
        val titleList = arrayOf("Movies", "Popular", "Top Rate", "Up Coming", "Now Playing")
        TabLayoutMediator(tabLayout, viewPager2, true) { tab, index ->
            val title = titleList[index]
            tab.text = title
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}