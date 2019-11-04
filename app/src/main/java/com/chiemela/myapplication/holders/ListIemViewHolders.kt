package com.chiemela.myapplication.holders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chiemela.myapplication.R
import com.chiemela.myapplication.activities.ActivityArticleDetail
import com.chiemela.myapplication.models.WikiPage
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ListIemViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.result_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View ->
            var detailPageIntent = Intent(itemView.context, ActivityArticleDetail::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage){
        if(page.thumbnail != null)
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)

        currentPage = page

        titleTextView.text = page.title

    }
}