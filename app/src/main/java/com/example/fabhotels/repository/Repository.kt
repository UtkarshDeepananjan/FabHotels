package com.example.fabhotels.repository

import androidx.lifecycle.MutableLiveData
import com.example.fabhotels.data.model.LoginUser
import com.example.fabhotels.data.model.NewsArticle
import com.example.fabhotels.network.ApiClient
import com.example.fabhotels.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiService1()
    }

    fun getNewsArticle(): MutableLiveData<NewsArticle?> {
        val mutableLiveData: MutableLiveData<NewsArticle?> = MutableLiveData<NewsArticle?>()
        val responseCall: Call<NewsArticle?>? = apiInterface!!.getNewsArticles()
        responseCall?.enqueue(object : Callback<NewsArticle?> {
            override fun onResponse(call: Call<NewsArticle?>, response: Response<NewsArticle?>) {
                if (response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<NewsArticle?>, t: Throwable) {}
        })
        return mutableLiveData
    }
    fun getLoginData(): MutableLiveData<LoginUser?> {
        val mutableLiveData: MutableLiveData<LoginUser?> = MutableLiveData<LoginUser?>()
        val responseCall: Call<LoginUser?>? = apiInterface!!.getLoginData()
        responseCall?.enqueue(object : Callback<LoginUser?> {
            override fun onResponse(call: Call<LoginUser?>, response: Response<LoginUser?>) {
                if (response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoginUser?>, t: Throwable) {}
        })
        return mutableLiveData
    }

}