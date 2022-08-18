package com.example.android.animefacts.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.android.animefacts.R
import com.example.android.animefacts.presentation.fragment.AnimeFactsFragment
import com.example.android.animefacts.presentation.fragment.HomeFragment
import com.example.android.animefacts.utilities.extention.navigateAndReplaceTo

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

    protected companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
    }

    fun setupActionBar(toolbar: Toolbar, title: String) {
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbar.setNavigationOnClickListener {
            requireActivity().navigateAndReplaceTo(homeFragment)
        }
    }
}