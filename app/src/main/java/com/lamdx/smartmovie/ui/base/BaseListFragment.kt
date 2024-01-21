package com.lamdx.smartmovie.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseListFragment<out T : ViewBinding> : Fragment() {

    private var _binding: T? = null

    protected var callback: Callback? = null

    protected val binding
        get() = _binding ?: throw IllegalStateException(
            "binding is only valid between onCreateView and onDestroyView"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = createBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): T

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initObserveLiveData()
//        initData()
//        initView()
//        initAction()
    }

//    abstract fun initObserveLiveData()
//
//    abstract fun initData()
//
//    abstract fun initView()
//
//    abstract fun initAction()

    interface Callback {
        fun onShowMovieDetail(movieId: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Callback instance is initialized
        if (context is Callback) callback = context
        else throw RuntimeException("$context must implement Callback")
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}