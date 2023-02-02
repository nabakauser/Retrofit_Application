package com.example.retrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter: MyAdapter by lazy {
        MyAdapter(this,
            articles = arrayListOf(),
            //onItemClick = this::makeToast
            onItemClick = this::navigateToNextScreen
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        getNews()
    }

    private fun setUpUi() {
        val uiRvNewsList = findViewById<RecyclerView>(R.id.uiRvNewsList)
        uiRvNewsList.adapter = adapter
    }

    private fun getNews() {
           val news = NewsService.newsInstance.getHeadLines("in", 1)
           news.enqueue(object : Callback<News> {
               override fun onFailure(call: Call<News>, t: Throwable) {
                   Log.d("tag", "error in fetching news", t)
               }

               override fun onResponse(call: Call<News>, response: Response<News>) {
                   val news = response.body()
                   if (news != null) {
                        adapter.onNewsListChanged(news.articles as List<Article>)
                   }
               }
           })
    }

    private fun navigateToNextScreen(article: Article) {
            val bundle = Bundle()
            bundle.putSerializable(KEY_ARTICLE,article)
            val intent = Intent(this, DisplayNewsActivity::class.java)
            intent.putExtra(KEY_BUNDLE_ARTICLE,bundle)
            startActivity(intent)

    }

    companion object{
        const val KEY_ARTICLE = "key.article"
        const val KEY_BUNDLE_ARTICLE = "key.bundle.article"
    }
}