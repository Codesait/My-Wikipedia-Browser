package com.chiemela.myapplication.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import com.chiemela.myapplication.R
import com.chiemela.myapplication.holders.CardViewHolders
import com.chiemela.myapplication.models.WikiPage

class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardViewHolders>() {

    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    // here we are indicating how many item the recyclerView will contain
    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holders:  CardViewHolders, position: Int) {
        var page = currentResults[position]

        holders.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolders {
       var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardViewHolders(cardItem)
    }
}