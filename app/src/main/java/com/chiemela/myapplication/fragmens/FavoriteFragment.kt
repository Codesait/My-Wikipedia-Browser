package com.chiemela.myapplication.fragmens

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chiemela.myapplication.R
import com.chiemela.myapplication.adapters.ArticleCardRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_favorite2.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    var favoriteRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite2, container, false)

        favoriteRecycler = view.findViewById<RecyclerView>(R.id.favorite_article_recycler)
        favoriteRecycler!!.layoutManager = LinearLayoutManager(context)
        favoriteRecycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}