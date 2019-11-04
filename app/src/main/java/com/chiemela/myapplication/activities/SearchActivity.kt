package com.chiemela.myapplication.activities

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.chiemela.myapplication.R
import com.chiemela.myapplication.adapters.ArticleListItemRecyclerAdapter
import com.chiemela.myapplication.models.WikiResult
import com.chiemela.myapplication.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.activity_article_detail.toolbar
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private val articleProvider : ArticleDataProvider = ArticleDataProvider()
    private  var adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        search_result_recycler.layoutManager= LinearLayoutManager(this)
        search_result_recycler.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home){
            finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu!!.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

               articleProvider.search("query",0,20) { WikiResult ->
                   adapter.currentResults.clear()
                   adapter.currentResults.addAll(WikiResult.query!!.pages)
                   runOnUiThread { adapter.notifyDataSetChanged() }
               }

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)

    }
}
