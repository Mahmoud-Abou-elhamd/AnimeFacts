package com.example.android.animefacts.data.repository

import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeFacts.AnimeFactsListModel
import com.example.android.animefacts.data.models.animeList.AnimeListModel
import com.example.android.animefacts.data.services.AnimeFactsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimeFactsRepository(private val animeFactsService: AnimeFactsService) {
    fun getAnimeList(): Flow<NetworkState<AnimeListModel>>{
        return flow {
            emit(NetworkState.Loading())
            emit(animeFactsService.getAnimeList())
        }
    }

    fun getAnimeFacts(animeName: String): Flow<NetworkState<AnimeFactsListModel>>{
        return flow {
            emit(NetworkState.Loading())
            emit(animeFactsService.getAnimeFacts(animeName))
        }
    }
}