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
import com.frogobox.frogoboxnews.presenter.TopHeadlineArticlesPresenter
import com.frogobox.frogoboxnews.view.activities.NewsDetailActivity
import com.frogobox.frogoboxnews.view.adapters.recyclerview.RecyclerViewAdapter
import com.frogobox.frogoboxnews.view.interfaces.contract.ArticlesContract
import kotlinx.android.synthetic.main.fragment_top_headline_technology.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class TopHeadlineTechnologyFragment : BaseFragment<ArticlesContract.TopHeadlinePresenter, ArticlesContract.View>(), ArticlesContract.View {

    private var articlesList: MutableList<Articles> = mutableListOf()
    private lateinit var adapter: RecyclerViewAdapter

    override fun createPresenter(): ArticlesContract.TopHeadlinePresenter {
        return TopHeadlineArticlesPresenter(activity!!.application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_headline_technology, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onGetTopHeadline("in", "technology")

        adapter = RecyclerViewAdapter(R.layout.content_list_news, context, articlesList){
            startActivity<NewsDetailActivity>(NewsDetailActivity.EXTRA_ARTICLES to it)
        }
        frg_tech_recyclerview.adapter = adapter
        frg_tech_recyclerview.layoutManager = LinearLayoutManager(context)

        frg_tech_swiperefresh.onRefresh {
            presenter.onGetTopHeadline("in", "general")
            frg_tech_progressbar.visibility = View.GONE
        }

    }

    override fun displayProgressIndicator() {
        frg_tech_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        frg_tech_progressbar.visibility = View.GONE
        frg_tech_swiperefresh.isRefreshing = false
    }

    override fun onDisplayArticles(articles: List<Articles>) {
        articlesList.clear()
        articlesList.addAll(articles)
        adapter.notifyDataSetChanged()
        frg_tech_swiperefresh.isRefreshing = false
    }

    override fun onDisplayErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
