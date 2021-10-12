package com.example.fabhotels.data.model


import com.google.gson.annotations.SerializedName

data class NewsArticle(
    @SerializedName("articles")
    val articles: MutableList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)