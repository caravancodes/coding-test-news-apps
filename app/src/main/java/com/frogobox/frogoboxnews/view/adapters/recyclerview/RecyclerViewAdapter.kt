package com.frogobox.frogoboxnews.view.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.frogoboxnews.R
import com.frogobox.frogoboxnews.model.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_list_news.view.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * FrogoBoxNews
 * Copyright (C) 16/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */

class RecyclerViewAdapter (private val context: Context?, private val data: List<Articles>, private var listener :
    (Articles) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_list_news, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val ctnTitle = view.ctn_title
        private val ctnAuthor = view.ctn_author
        private val ctnDescription = view.ctn_description
        private val ctnPoster = view.ctn_poster

        fun bindItem(articlesList: Articles, listener: (Articles) -> Unit) {
            ctnTitle.text = articlesList.title
            ctnAuthor.text = articlesList.publishedAt
            ctnDescription.text = articlesList.description
            Picasso.get().load(articlesList.urlToImage).into(ctnPoster)
            itemView.setOnClickListener {
                listener(articlesList)
            }
        }
    }
}