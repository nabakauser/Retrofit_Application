package com.example.retrofitapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country")country: String,
                     @Query("page")page: Int): Call<News>
    //https://newsapi.org/v2/top-headlines?apiKey=55818053ca304dd389c7d3fe367a5d58&country=in&page=1

}