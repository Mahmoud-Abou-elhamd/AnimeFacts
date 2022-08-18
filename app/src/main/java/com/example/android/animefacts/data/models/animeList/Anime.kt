package com.example.android.animefacts.data.models.animeList

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("anime_id")
    val id: Int,
    @SerializedName("anime_name")
    val name: String,
    @SerializedName("anime_img")
    val image: String
)
