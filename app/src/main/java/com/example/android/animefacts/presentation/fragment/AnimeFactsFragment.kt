package com.example.android.animefacts.presentation.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.android.animefacts.R
import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeFacts.AnimeFactsListModel
import com.example.android.animefacts.data.repository.AnimeFactsRepository
import com.example.android.animefacts.data.services.AnimeFactsService
import com.example.android.animefacts.databinding.FragmentFactsBinding
import com.example.android.animefacts.presentation.base.BaseFragment
import kotlinx.coroutines.launch

class AnimeFactsFragment : BaseFragment<FragmentFactsBinding>() {
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentFactsBinding =
        FragmentFactsBinding::inflate

    override fun setup() {
        getAnimeFacts()
        setupActionBar(
            toolbar = binding.factsToolbar.toolbar,
            title = resources.getString(R.string.Facts)
        )
    }

    private fun getAnimeFacts(){
        val animeName = "/${arguments?.getString(KEY_NAME)}"
        lifecycleScope.launch {
            AnimeFactsRepository(AnimeFactsService).getAnimeFactsNetworkStateFlow(animeName).collect(::onGetResponse)
        }
    }

    private fun onGetResponse(state: NetworkState<AnimeFactsListModel>){
        when(state){
            is NetworkState.Loading -> displayLoadingVisibilityState()
            is NetworkState.Failure -> {
                displayNormalVisibilityState()
                displayFailureState()
            }
            is NetworkState.Success -> {
                displayNormalVisibilityState()
                displayAnimeFactsList(state)
            }
        }
    }

    private fun displayLoadingVisibilityState(){
        binding.apply {
            group.visibility = View.INVISIBLE
            animationLoding.visibility = View.VISIBLE
        }
    }

    private fun displayNormalVisibilityState(){
        binding.apply {
            group.visibility = View.VISIBLE
            animationLoding.visibility = View.INVISIBLE
        }
    }

    private fun displayFailureState() {
        binding.apply {
            animationLoding.setAnimation(R.raw.icon_error)
            animationLoding.playAnimation()
        }
    }

    private fun displayAnimeFactsList(state: NetworkState.Success<AnimeFactsListModel>){
        Glide.with(this).load(state.transferredData.image).into(binding.animeImg)
        binding.nameText.text = arguments?.getString(KEY_NAME)

        var factsDetails = ""
        val factsList = state.transferredData.animeFactsList
        factsList.forEach { fact ->
            factsDetails += "${fact.content}\n\n"
        }
        binding.factsDetailsText.text = factsDetails
    }
}