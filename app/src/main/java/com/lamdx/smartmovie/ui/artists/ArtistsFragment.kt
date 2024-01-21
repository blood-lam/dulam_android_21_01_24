package com.lamdx.smartmovie.ui.artists

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
import com.lamdx.smartmovie.databinding.FragmentArtistsBinding

class ArtistsFragment : Fragment() {

    private var _binding: FragmentArtistsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ArtistsFragment()
    }

    private lateinit var viewModel: ArtistsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        activity?.findViewById<TextView>(R.id.toolbar_title)?.text = "Artist"
        val toolbar = activity?.findViewById<Toolbar>(R.id.cus_toolbar)
        toolbar?.let {
            it.menu.iterator().forEach { menuItem -> menuItem.setVisible(false) }
        }

        viewModel = ViewModelProvider(this)[ArtistsViewModel::class.java]
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragmentArtist
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}