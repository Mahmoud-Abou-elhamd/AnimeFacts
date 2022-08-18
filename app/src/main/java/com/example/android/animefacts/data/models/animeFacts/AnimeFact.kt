package com.example.android.animefacts.data.models.animeFacts

import com.google.gson.annotations.SerializedName

data class AnimeFact(
    @SerializedName("fact_id")
    val id: Int,
    @SerializedName("fact")
    val content: String
)
