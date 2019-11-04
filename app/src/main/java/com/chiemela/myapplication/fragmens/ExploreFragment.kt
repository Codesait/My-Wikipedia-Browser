package com.chiemela.myapplication.fragmens


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chiemela.myapplication.R
import com.chiemela.myapplication.activities.SearchActivity
import com.chiemela.myapplication.adapters.ArticleCardRecyclerAdapter
import com.chiemela.myapplication.models.WikiResult
import com.chiemela.myapplication.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.fragment_explore.*


/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {
    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    var refresher: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)

        searchCardView!!.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomAricles()
        }

        getRandomAricles()
        return view
    }

    private fun getRandomAricles(){
        try {


            articleProvider.getRandom(15, { WikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(WikiResult.query!!.pages)
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                }

            })
        }catch (ex :Exception){
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("oops!")
            val dialog = builder.create()
            dialog.show()
        }
    }
}
