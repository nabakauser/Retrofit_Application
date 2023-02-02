package com.example.retrofitapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "55818053ca304dd389c7d3fe367a5d58"


object NewsService {
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }

    private fun getRetrofitClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                return@addInterceptor chain.proceed(builder.build())
            }
            .build()

    }

    //private fun doAddition(a: Int, b: Int) = a + b -> in kotlin, you can directly store the return value in func declaration

    private fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}