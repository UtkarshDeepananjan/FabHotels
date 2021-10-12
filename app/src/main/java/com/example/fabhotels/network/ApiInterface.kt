package com.example.fabhotels.network

import com.example.fabhotels.data.model.LoginUser
import com.example.fabhotels.data.model.NewsArticle
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/7c27fa874f0a4d46e4d4")
    fun getNewsArticles(): Call<NewsArticle?>?

    @GET("/0774724810730d4ee184")
    fun getLoginData(): Call<LoginUser?>?
}