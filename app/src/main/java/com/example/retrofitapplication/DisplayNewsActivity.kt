package com.example.retrofitapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.retrofitapplication.MainActivity.Companion.KEY_ARTICLE
import com.example.retrofitapplication.extensions.formatTo
import com.example.retrofitapplication.extensions.toDate
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class DisplayNewsActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_news)

        val uiTvTileDisplay: TextView = findViewById(R.id.uiTvTitle)
        val uiIvImageView: ImageView = findViewById(R.id.uiIvImageView)
        val uiTvAuthor: TextView = findViewById(R.id.uiTvAuthor)
        val uiTvContent: TextView = findViewById(R.id.uiTvContent)
        val uiTvDateAndTime: TextView = findViewById(R.id.uiTvDateAndTime)

        val bundle = intent.extras?.getBundle(MainActivity.KEY_BUNDLE_ARTICLE)
        val article = bundle?.getSerializable(KEY_ARTICLE) as Article


        uiTvTileDisplay.text = article.title
        uiTvAuthor.text = getString(R.string.author) + " " + article.author
        uiTvContent.text = article.content
        Glide.with(this).load(article.urlToImage).into(uiIvImageView)
        val pa = Instant.parse( article.publishedAt ).atOffset( ZoneOffset.UTC ).format(
            DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" ))
        uiTvDateAndTime.text =getString(R.string.publishedAt) + " " + pa.toDate().formatTo("dd-MM-yyyy HH:mm:ss")



        Log.d("title", "onCreate: ${article.title}")
        Log.d("author", "onCreate: ${article.author}")
    }


}