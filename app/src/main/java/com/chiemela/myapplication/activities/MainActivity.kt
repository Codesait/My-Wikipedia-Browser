package com.chiemela.myapplication.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.chiemela.myapplication.R
import com.chiemela.myapplication.fragmens.ExploreFragment
import com.chiemela.myapplication.fragmens.FavoriteFragment
import com.chiemela.myapplication.fragmens.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment
    private val favoriteFragment: FavoriteFragment
    private val historyFragment: HistoryFragment

    // initializing classes
    init {
        exploreFragment = ExploreFragment()
        favoriteFragment = FavoriteFragment()
        historyFragment = HistoryFragment()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transaction =supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when(item.itemId){
            R.id.navigation_explore ->transaction.replace(R.id.fragment_container, exploreFragment)
            R.id.navigation_favorite ->transaction.replace(R.id.fragment_container, favoriteFragment)
            R.id.navigation_history ->transaction.replace(R.id.fragment_container, historyFragment)

        }

        transaction.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //here we are making explore fragment the first fragment when app loads
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }
}
