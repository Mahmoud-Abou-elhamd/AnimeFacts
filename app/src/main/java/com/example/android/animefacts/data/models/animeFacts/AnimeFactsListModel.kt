package com.example.android.animefacts.data.models.animeFacts

data class AnimeFactsListModel(
    @SerializedName("img")
    val image: String,
    @SerializedName("data")
    val animeFactsList: List<AnimeFact>
)
