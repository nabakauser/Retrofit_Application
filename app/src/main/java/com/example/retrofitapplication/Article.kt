package com.example.retrofitapplication

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage:String?,
    val publishedAt: String?,
    val content: String?
): Serializable
    //@SerializedName("urlToImage") - in case of naming conventions, to change the data class object name acc to your preference

    /* val authorTitle : String = author + title - when you have more than one data class(ref in backend= object)
    you can create a new variable and append it here */
