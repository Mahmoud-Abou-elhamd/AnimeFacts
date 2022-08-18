package com.example.android.animefacts.data.repository

import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeList.AnimeListModel
import com.example.android.animefacts.data.services.AnimeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AnimeRepository(private val animeService: AnimeService) {
    suspend fun getAnimeListNetworkStateFlow(): Flow<NetworkState<AnimeListModel>> {
        return flow {
            emit(NetworkState.Loading())
            emit(animeService.getAnimeListNetworkState())
        }.flowOn(Dispatchers.IO)
    }
}