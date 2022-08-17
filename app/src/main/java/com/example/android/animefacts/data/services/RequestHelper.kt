package com.example.android.animefacts.data.services

import com.example.android.animefacts.utilities.Constants
import okhttp3.Request

object RequestHelper {
    fun makeAnimeFactsRequset(animeName: String): Request{
        return Request.Builder().url(Constants.baseUrl + animeName).build()
    }
}