package com.dathanasleri.newsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dathanasleri.newsapp.R
import com.dathanasleri.newsapp.models.ArticleData
import com.squareup.picasso.Picasso


class ArticlesRecyclerViewAdapter: RecyclerView.Adapter<ArticlesRecyclerViewAdapter.MyViewHolder>() {
    private var articles = ArrayList<ArticleData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(articles : ArrayList<ArticleData>) {
        this.articles = articles
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val articleImage: ImageView = view.findViewById(R.id.articleImage)
        private val articleTitle: TextView = view.findViewById(R.id.articleTitle)
        private val articleDescription: TextView = view.findViewById(R.id.articleDescription)

        fun bind(data: ArticleData) {
            articleTitle.text = data.title
            articleDescription.text = data.description

            val url = data.urlToImage
            Picasso.get().load(url).into(articleImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_cell, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}