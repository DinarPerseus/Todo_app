package com.example.myapplication.ui.news

import NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String = "technology",
        @Query("apiKey") apiKey: String = "YOUR_API_KEY"
    ): Call<NewsResponse>
}