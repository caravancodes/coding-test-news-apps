package com.frogobox.frogoboxnews.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.frogobox.frogoboxnews.R
import com.frogobox.frogoboxnews.view.fragments.EverythingFragment
import com.frogobox.frogoboxnews.view.fragments.TopHeadlineFragment
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
}