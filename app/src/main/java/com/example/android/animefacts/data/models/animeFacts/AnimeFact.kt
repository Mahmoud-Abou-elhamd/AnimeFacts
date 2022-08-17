package com.example.android.animefacts.data.models.animeFacts

data class AnimeFact(
    @SerializedName("fact_id")
    val id: Int,
    val fact: String
)
