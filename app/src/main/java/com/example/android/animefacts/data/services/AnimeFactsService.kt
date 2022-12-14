package com.example.android.animefacts.data.services

import com.example.android.animefacts.data.NetworkState
import com.example.android.animefacts.data.models.animeFacts.AnimeFactsListModel
import com.google.gson.Gson
import okhttp3.OkHttpClient

object AnimeFactsService {
    private val client = OkHttpClient()
    fun getAnimeFactsNetworkState(animeName: String): NetworkState<AnimeFactsListModel>{
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