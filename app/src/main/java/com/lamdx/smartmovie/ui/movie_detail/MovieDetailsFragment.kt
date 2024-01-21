package com.lamdx.smartmovie.ui.movie_detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.model.MovieDetails
import com.lamdx.smartmovie.ui.movie_detail.tabs.MovieDetailPagerAdapter
import com.lamdx.smartmovie.ui.movie_detail.tabs.cast.TabCastFragment
import com.lamdx.smartmovie.ui.movie_detail.tabs.similar_movie.TabSimilarMovieFragment
import com.lamdx.smartmovie.utils.Constant
import com.lamdx.smartmovie.utils.PosterSizes
import com.lamdx.smartmovie.utils.imagePresent
import com.lamdx.smartmovie.utils.isEllipsized
import com.lamdx.smartmovie.utils.waitForLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var movieId: Int? = null

    private val viewModel by activityViewModels<MovieDetailsViewModel>()

    private val MAX_LINE_COUNT = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val toolbarTextView = activity?.findViewById<TextView>(R.id.toolbar_title)
        toolbarTextView?.text = "Movie Details"

        val toolbar = activity?.findViewById<Toolbar>(R.id.cus_toolbar)
        toolbar?.let {
            it.menu.iterator().forEach { menuItem -> menuItem.setVisible(false) }
        }

        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        arguments?.let {
            movieId = it.getInt("movieId")
        }

        movieId?.let {
            loadMovieDetails(view, it)
            adjustMovieOverview(view)
        }

        activity?.findViewById<Toolbar>(R.id.cus_toolbar)?.let { createBackButton(it) }

        return view
    }


    private fun setupViewPager(viewPager2: ViewPager2, initData: MovieDetails) {
        val tabFragments = arrayListOf(
            TabCastFragment(), TabSimilarMovieFragment()
        )
        viewPager2.adapter =
            MovieDetailPagerAdapter(tabFragments, initData, childFragmentManager, lifecycle)
    }

    private fun setTabLayout(tabLayout: TabLayout, viewPager2: ViewPager2) {
        val titleList = arrayOf("Cast", "Similar Movies")
        TabLayoutMediator(tabLayout, viewPager2, true) { tab, index ->
            val title = titleList[index]
            tab.text = title
        }.attach()
    }

    private fun loadMovieDetails(view: View, movieId: Int) {
        val imgView = view.findViewById<ImageView>(R.id.detail_movie_poster)
        val title = view.findViewById<TextView>(R.id.detail_movie_title)
        val genres = view.findViewById<TextView>(R.id.detail_movie_genres)
        val language = view.findViewById<TextView>(R.id.detail_movie_language)
        val releaseDate = view.findViewById<TextView>(R.id.detail_movie_date)
        val overview = view.findViewById<TextView>(R.id.detail_movie_overview)
        val rate = view.findViewById<RatingBar>(R.id.detail_movie_rate)
        val viewPager = view.findViewById<ViewPager2>(R.id.movie_detail_view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.movie_detail_tabs)
        viewModel.viewModelScope.launch {
            viewModel.movieDetailFLow(movieId).collect { detail ->

                setupViewPager(viewPager, detail)
                setTabLayout(tabLayout, viewPager)

                val imgUrl =
                    "${Constant.IMG_HOST}/${PosterSizes.original.toString() + detail.posterPath}"
                imagePresent(view, imgUrl, imgView)
                title.text = detail.title
                genres.text = detail.genres.map { gen -> gen.name }
                    .reduce { acc: String, name: String -> "$acc | $name" }
                language.text =
                    detail.spokenLanguages.find { lang -> lang.iso639P1.lowercase() == detail.originalLanguage }?.name
                releaseDate.text = detail.releaseDate
                rate.rating = detail.voteAverage / 2f

                overview.text = detail.overview
            }
        }
    }

    private fun adjustMovieOverview(view: View) {
        val overview = view.findViewById<TextView>(R.id.detail_movie_overview)
        val expand = view.findViewById<TextView>(R.id.detail_movie_overview_expand)

        view.waitForLayout {
            if (overview.isEllipsized()) {
                expand?.visibility = TextView.VISIBLE
                overview.setOnClickListener {
                    collapseExpandTextView(it as TextView)
                }
            } else {
                expand?.visibility = TextView.GONE
            }
        }
    }

    private fun collapseExpandTextView(tv: TextView) {
        if (tv.maxLines == MAX_LINE_COUNT) {
            // collapsed - expand it
            tv.ellipsize = null
            tv.maxLines = Int.MAX_VALUE
        } else {
            // expanded - collapse it
            tv.ellipsize = TextUtils.TruncateAt.END
            tv.maxLines = MAX_LINE_COUNT
        }
        val animation = ObjectAnimator.ofInt(tv, "maxLines", tv.maxLines)
        animation.setDuration(200).start()
    }

    private fun createBackButton(toolBar: Toolbar) {
        toolBar.setNavigationOnClickListener { // Do something
            parentFragmentManager.popBackStack()
        }
    }

}