package com.example.android.animefacts.data.models.animeList

import com.google.gson.annotations.SerializedName

data class AnimeListModel(
    @SerializedName("data")
    val animeList: List<Anime>
)
