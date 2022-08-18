package com.example.android.animefacts.data.models.animeFacts

import com.google.gson.annotations.SerializedName

data class AnimeFactsListModel(
    @SerializedName("img")
    val image: String,
    @SerializedName("data")
    val animeFactsList: List<AnimeFact>
)
