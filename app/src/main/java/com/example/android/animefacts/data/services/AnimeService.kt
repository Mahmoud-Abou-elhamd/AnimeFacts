package com.example.android.animefacts.data.services

import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeList.AnimeListModel
import com.google.gson.Gson
import okhttp3.OkHttpClient

object AnimeService {
    private val client = OkHttpClient()
    fun getAnimeListNetworkState(): NetworkState<AnimeListModel> {
        val response = client.newCall(RequestHelper.makeAnimeFactsRequset("")).execute()
        return if(response.isSuccessful){
            Gson().fromJson(response.body?.string(), AnimeListModel::class.java).run{
                NetworkState.Success(this)
            }
        }else{
            NetworkState.Failure(response.message)
        }
    }
}