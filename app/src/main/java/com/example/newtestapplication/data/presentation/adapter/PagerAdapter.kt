package com.example.newtestapplication.data.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newtestapplication.R
import com.example.newtestapplication.data.loadImage
import com.example.newtestapplication.data.Article
import kotlinx.android.synthetic.main.item_new_list.view.*

class PagerAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<PagerAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun setData(article: Article) {
            itemView.apply {
                tvTitle.text = article.title
                tvSource.text = article.source.name
                ivNews.loadImage(article.urlToImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_new_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(articles[position])
    }
}