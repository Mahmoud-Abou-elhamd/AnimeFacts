package com.example.android.animefacts.data.models.animeList

data class AnimeListModel(
    @SerializedName("data")
    val animeList: List<Anime>
)
