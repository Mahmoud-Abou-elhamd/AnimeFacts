package com.example.android.animefacts.data.services

import android.net.Network
import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeFacts.AnimeFact
import com.example.android.animefacts.data.models.animeFacts.AnimeFactsListModel
import com.example.android.animefacts.data.models.animeList.Anime
import com.example.android.animefacts.data.models.animeList.AnimeListModel
import com.google.gson.Gson
import okhttp3.OkHttpClient

/*interface BaseAnimeFactsService{
    fun getAnimeList(): NetworkState<AnimeListModel>
    fun getAnimeFacts(animeName: String) : NetworkState<AnimeFactsListModel>
}*/

object AnimeFactsService {
    private val client = OkHttpClient()
    fun getAnimeList(): NetworkState<AnimeListModel>{
        val response = client.newCall(RequestHelper.makeAnimeFactsRequset("")).execute()
        return if(response.isSuccessful){
            Gson().fromJson(response.body?.string(), AnimeListModel::class.java).run{
                NetworkState.Success(this)
            }
        }else{
            NetworkState.Failure(response.message)
        }
    }
    fun getAnimeFacts(animeName: String): NetworkState<AnimeFactsListModel>{
        val response = client.newCall(RequestHelper.makeAnimeFactsRequset(animeName)).execute()
        return if(response.isSuccessful){
            Gson().fromJson(response.body?.string(), AnimeFactsListModel::class.java).run{
                NetworkState.Success(this)
            }
        }else{
            NetworkState.Failure(response.message)
        }
    }
}