package com.frogobox.news.mvp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.frogobox.news.R
import com.frogobox.news.mvp.about.AboutApiActivity
import com.frogobox.news.mvp.everything.EverythingFragment
import com.frogobox.news.mvp.topheadline.TopHeadlineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(TopHeadlineFragment())
        setTitle(R.string.title_top_headline)
        supportActionBar?.elevation = 0f

        act_main_bottom_nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_headline -> {
                    openFragment(TopHeadlineFragment())
                    setTitle(R.string.title_top_headline)
                }
                R.id.navigation_everything -> {
                    openFragment(EverythingFragment())
                    setTitle(R.string.title_everything)
                }
            }
            true
        }

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.act_main_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.toolbar_about -> {
                val i = Intent(this, AboutApiActivity::class.java)
                startActivity(i)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}