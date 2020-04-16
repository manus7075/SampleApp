/*
 * Copyright (c) 2020.  Gallup, Inc. All rights reserved
 * NewStaggeredAdapter.kt
 */

package com.example.newtestapplication.data.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newtestapplication.R
import com.example.newtestapplication.data.Article
import com.example.newtestapplication.data.loadImage
import kotlinx.android.synthetic.main.item_new_list.view.*
import kotlinx.android.synthetic.main.list_item_staggerd.view.*

class NewStaggeredAdapter (val list:List<Article>):RecyclerView.Adapter<NewStaggeredAdapter.NewsViewHolder>(){




    inner class NewsViewHolder( itemView: View):RecyclerView.ViewHolder(itemView){
        val newsItemTitle = itemView.tvTitle
        val newsImage = itemView.ivNews;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NewsViewHolder {
      val view =LayoutInflater.from(parent.context).inflate(R.layout.item_new_list,parent,false)

        return NewsViewHolder(view)

    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = list[position]
        holder.newsImage.loadImage(article.urlToImage)
        holder.newsItemTitle.text=article.title
    }
}