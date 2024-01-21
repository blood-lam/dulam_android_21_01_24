package com.lamdx.smartmovie.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.databinding.FragmentSearchBinding
import com.lamdx.smartmovie.utils.getQueryTextChangeStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val searchViewModel by activityViewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        activity?.findViewById<TextView>(R.id.toolbar_title)?.text = "Search"
        val toolbar = activity?.findViewById<Toolbar>(R.id.cus_toolbar)
        toolbar?.let {
            it.menu.findItem(R.id.toolbar_view_mode).setVisible(false)
            it.menu.findItem(R.id.toolbar_search_symbol).setVisible(true)
        }
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchConfig(root)

        return root
    }

    private fun searchConfig(root: View) {
        val searchView = root.findViewById<SearchView>(R.id.search_movie)
        searchViewModel.searchedMovie = searchView.getQueryTextChangeStateFlow()
            .debounce(300)
            .filter { query ->
                return@filter !query.isEmpty()
            }
            .distinctUntilChanged()
            .flatMapLatest {
                searchViewModel.searchFlow(it, 1)
            }
            .flowOn(Dispatchers.IO)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}