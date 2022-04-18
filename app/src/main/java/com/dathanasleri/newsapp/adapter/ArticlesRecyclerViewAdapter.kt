package com.dathanasleri.newsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dathanasleri.newsapp.R
import com.dathanasleri.newsapp.models.Article
import com.squareup.picasso.Picasso

class ArticlesRecyclerViewAdapter(private val onArticleListener: OnArticleListener): RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ArticleViewHolder>() {
    private var articles = ArrayList<Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(articles : ArrayList<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val articleImage: ImageView = view.findViewById(R.id.articleImage)
        private val articleTitle: TextView = view.findViewById(R.id.articleTitle)
        private val articleDescription: TextView = view.findViewById(R.id.articleDescription)

        fun bind(data: Article, clickListener: OnArticleListener) {
            articleTitle.text = data.title
            articleDescription.text = data.description

            val url = data.urlToImage
            Picasso.get().load(url).into(articleImage)

            itemView.setOnClickListener {
                clickListener.onArticleClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_cell, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position], onArticleListener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    interface OnArticleListener {
        fun onArticleClick(article: Article)

    }
}