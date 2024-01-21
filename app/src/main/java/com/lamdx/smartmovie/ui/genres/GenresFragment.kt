package com.lamdx.smartmovie.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.databinding.FragmentGenresBinding

class GenresFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        activity?.findViewById<TextView>(R.id.toolbar_title)?.text = "Genres"
        val toolbar = activity?.findViewById<Toolbar>(R.id.cus_toolbar)
        toolbar?.let {
            it.menu.iterator().forEach { menuItem -> menuItem.setVisible(false) }
        }

        val genresViewModel = ViewModelProvider(this)[GenresViewModel::class.java]

        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGenres
        genresViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}