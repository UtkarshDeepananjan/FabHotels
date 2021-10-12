package com.example.fabhotels.network

import com.example.fabhotels.utils.Constants
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofit: Retrofit? = null


    fun getApiService1(): ApiInterface? {
        val networkInterceptor = Interceptor { chain: Interceptor.Chain ->
            // set max-age and max-stale properties for cache header
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.HOURS)
                .maxStale(3, TimeUnit.DAYS)
                .build()
            chain.proceed(chain.request())
                .newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

            val httpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(networkInterceptor)
                .addInterceptor(logging).build()
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.ARTICLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
        return retrofit!!.create(ApiInterface::class.java)
    }
    fun getApiService2(): ApiInterface? {
        val networkInterceptor = Interceptor { chain: Interceptor.Chain ->
            // set max-age and max-stale properties for cache header
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.HOURS)
                .maxStale(3, TimeUnit.DAYS)
                .build()
            chain.proceed(chain.request())
                .newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

            val httpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(networkInterceptor)
                .addInterceptor(logging).build()
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
        return retrofit!!.create(ApiInterface::class.java)
    }
}