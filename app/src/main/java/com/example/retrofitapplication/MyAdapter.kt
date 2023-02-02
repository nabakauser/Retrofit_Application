package com.example.retrofitapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapplication.extensions.replaceSpaceAndHyphen

class MyAdapter(private val context: Context,
                private val articles: MutableList<Article>,
                private val onItemClick: (Article) -> Unit,
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val response = articles[position]
        with(holder) {
            uiTvTitle.text = response.title
            Glide.with(context)
                .load(response.urlToImage)
                .into(holder.uiIvImage)
            if (response.description.isNullOrEmpty()) {
                uiTvDescription.visibility = View.GONE
            } else {
                uiTvDescription.text = response.description
            }

        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun onNewsListChanged(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uiTvTitle: TextView = itemView.findViewById(R.id.uiTvTitle)
        var uiTvDescription: TextView = itemView.findViewById(R.id.uiTvContent)
        var uiIvImage: ImageView = itemView.findViewById(R.id.uiIvImageView)

        init {
            uiTvTitle.setOnClickListener { onItemClick(articles[adapterPosition]) }
        }
    }
}

