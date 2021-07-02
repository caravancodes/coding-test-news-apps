package com.frogobox.news.mvp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.news.R
import com.frogobox.news.model.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ARTICLES = "extra_articles"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        setTitle(R.string.title_detail_news)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(EXTRA_ARTICLES)) {
            // -------------------------------------------------------------------------------------
            val extraArticles = intent.getParcelableExtra<Articles>(EXTRA_ARTICLES)
            val extraArticlesTitle = extraArticles?.title
            val extraArticlesAuthor = extraArticles?.author
            val extraArticlesDescription = extraArticles?.description
            val extraArticlesDate = extraArticles?.publishedAt
            val extraArticlesContent = extraArticles?.content
            val extraArticlesPoster = extraArticles?.urlToImage

            act_detail_title.text = extraArticlesTitle
            act_detail_author.text = extraArticlesAuthor
            act_detail_description.text = extraArticlesDescription
            act_detail_date.text = extraArticlesDate
            act_detail_content.text = extraArticlesContent
            Picasso.get().load(extraArticlesPoster).into(act_detail_poster)
            // -------------------------------------------------------------------------------------
        }

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
