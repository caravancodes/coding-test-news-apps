package com.frogobox.frogoboxnews.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frogobox.frogoboxnews.R

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ARTICLES = "extra_articles"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
    }
}
