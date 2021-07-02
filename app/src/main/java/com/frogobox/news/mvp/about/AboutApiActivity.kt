package com.frogobox.news.mvp.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.news.R

class AboutApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_api)

        setTitle(R.string.title_about_api)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
