package com.frogobox.frogoboxnews.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.frogobox.frogoboxnews.R
import com.frogobox.frogoboxnews.base.BaseFragment
import com.frogobox.frogoboxnews.model.Articles
import com.frogobox.frogoboxnews.presenter.EverythingArticlesPresenter
import com.frogobox.frogoboxnews.view.activities.NewsDetailActivity
import com.frogobox.frogoboxnews.view.adapters.recyclerview.RecyclerViewAdapter
import com.frogobox.frogoboxnews.view.interfaces.contract.ArticlesContract
import kotlinx.android.synthetic.main.fragment_everything.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class EverythingFragment : BaseFragment<ArticlesContract.EverythingPresenter, ArticlesContract.View>(), ArticlesContract.View {

    private var articlesList: MutableList<Articles> = mutableListOf()
    private lateinit var adapter: RecyclerViewAdapter

    override fun createPresenter(): ArticlesContract.EverythingPresenter {
        return EverythingArticlesPresenter(activity!!.application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_everything, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onGetEverything("onepiece")

        adapter = RecyclerViewAdapter(R.layout.content_list_news, context, articlesList){
            startActivity<NewsDetailActivity>(NewsDetailActivity.EXTRA_ARTICLES to it)
        }
        frg_everything_recyclerview.adapter = adapter
        frg_everything_recyclerview.layoutManager = LinearLayoutManager(context)

        frg_everything_swiperefresh.onRefresh {
            frg_everything_progressbar.visibility = View.GONE
            presenter.onGetEverything("onepiece")
        }

    }

    override fun displayProgressIndicator() {
        frg_everything_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        frg_everything_progressbar.visibility = View.GONE
        frg_everything_swiperefresh.isRefreshing = false
    }

    override fun onDisplayArticles(articles: List<Articles>) {
        articlesList.clear()
        articlesList.addAll(articles)
        adapter.notifyDataSetChanged()
        frg_everything_swiperefresh.isRefreshing = false
    }

    override fun onDisplayErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
