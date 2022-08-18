package com.example.android.animefacts.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.android.animefacts.R
import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeList.AnimeListModel
import com.example.android.animefacts.data.repository.AnimeRepository
import com.example.android.animefacts.data.services.AnimeService
import com.example.android.animefacts.databinding.FragmentHomeBinding
import com.example.android.animefacts.presentation.adapter.AnimeAdapter
import com.example.android.animefacts.presentation.base.BaseFragment
import com.example.android.animefacts.presentation.interfaces.AnimeListener
import com.example.android.animefacts.utilities.extention.navigateAndReplaceTo
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(), AnimeListener {
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun setup() {
        getAnimeListItems()
    }

    private fun getAnimeListItems(){
        lifecycleScope.launch {
            AnimeRepository(AnimeService).getAnimeListNetworkStateFlow().collect(::onGetResponse)
        }
    }

    private fun onGetResponse(state: NetworkState<AnimeListModel>){
        when(state){
            is NetworkState.Loading -> displayLoadingVisibilityState()
            is NetworkState.Failure -> {
                displayNormalVisibilityState()
                displayFailureState()
            }
            is NetworkState.Success -> {
                displayNormalVisibilityState()
                displayAnimeList(state)
            }
        }
    }

    private fun displayLoadingVisibilityState(){
        binding.apply {
            group.visibility = View.GONE
            animationLoding.visibility = View.VISIBLE
        }
    }

    private fun displayNormalVisibilityState(){
        binding.apply {
            group.visibility = View.VISIBLE
            animationLoding.visibility = View.GONE
        }
    }

    private fun displayFailureState() {
        binding.apply {
            animationLoding.setAnimation(R.raw.icon_error)
            animationLoding.playAnimation()
        }
    }

    private fun displayAnimeList(state: NetworkState.Success<AnimeListModel>){
        val animeAdapter = AnimeAdapter(state.transferredData.animeList, this)
        binding.animeList.adapter = animeAdapter
    }

    override fun onClickItem(id: Int, name: String) {
        val bundle = setupNavigateData(id, name)
        navigateToFacts(bundle)
    }

    private fun setupNavigateData(id: Int, name: String) : Bundle {
        val bundle = Bundle()
        bundle.putString(KEY_ID,id.toString())
        bundle.putString(KEY_NAME,name)
        return bundle
    }

    private fun navigateToFacts(bundle: Bundle){
        requireActivity().navigateAndReplaceTo(animeFactsFragment, bundle)
    }
}