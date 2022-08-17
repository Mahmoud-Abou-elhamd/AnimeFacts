package com.example.android.animefacts.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.android.animefacts.presentation.fragment.AnimeFactsFragment
import com.example.android.animefacts.presentation.fragment.HomeFragment

abstract class BaseFragment<VB: ViewBinding> : Fragment() {
    abstract fun setup()
    lateinit var homeFragment: HomeFragment
    lateinit var animeFactsFragment: AnimeFactsFragment

    abstract val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> VB
    private lateinit var _binding: VB
    protected val binding: VB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        initFragments()
        setup()
        return _binding.root
    }

    private fun initFragments(){
        homeFragment = HomeFragment()
        animeFactsFragment = AnimeFactsFragment()
    }
}