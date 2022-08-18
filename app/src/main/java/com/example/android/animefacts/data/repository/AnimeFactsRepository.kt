package com.example.android.animefacts.data.repository

import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeFacts.AnimeFactsListModel
import com.example.android.animefacts.data.services.AnimeFactsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class AnimeFactsRepository(private val animeFactsService: AnimeFactsService) {
    suspend fun getAnimeFactsNetworkStateFlow(animeName: String): Flow<NetworkState<AnimeFactsListModel>>{
        return flow {
            emit(NetworkState.Loading())
            emit(animeFactsService.getAnimeFactsNetworkState(animeName))
        }.flowOn(Dispatchers.IO)
    }
}