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
import kotlinx.android.synthetic.main.fragment_top_headline_general.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TopHeadlineGeneralFragment : BaseFragment<ArticlesContract.TopHeadlinePresenter, ArticlesContract.View>(), ArticlesContract.View {

    private var articlesList: MutableList<Articles> = mutableListOf()
    private var articlesListGeneral: MutableList<Articles> = mutableListOf()
    private var articlesListSport: MutableList<Articles> = mutableListOf()
    private var articlesListScience: MutableList<Articles> = mutableListOf()

    private lateinit var adapterGeneral: RecyclerViewAdapter
    private lateinit var adapterSport: RecyclerViewAdapter
    private lateinit var adapterScience: RecyclerViewAdapter

    override fun createPresenter(): ArticlesContract.TopHeadlinePresenter {
        return TopHeadlineArticlesPresenter(activity!!.application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_headline_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataArrayList()

        adapterGeneral = RecyclerViewAdapter(R.layout.content_list_news_horizontal,context, articlesList){
            startActivity<NewsDetailActivity>(NewsDetailActivity.EXTRA_ARTICLES to it)
        }

        adapterSport = RecyclerViewAdapter(R.layout.content_list_news_horizontal,context, articlesList){
            startActivity<NewsDetailActivity>(NewsDetailActivity.EXTRA_ARTICLES to it)
        }

        adapterScience = RecyclerViewAdapter(R.layout.content_list_news_horizontal,context, articlesList){
            startActivity<NewsDetailActivity>(NewsDetailActivity.EXTRA_ARTICLES to it)
        }

        frg_gen_recyclerview_general.adapter = adapterGeneral
        frg_gen_recyclerview_sport.adapter = adapterSport
        frg_gen_recyclerview_science.adapter = adapterScience

        frg_gen_recyclerview_general.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        frg_gen_recyclerview_science.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        frg_gen_recyclerview_sport.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        frg_gen_swiperefresh.onRefresh {
//            frg_gen_progressbar.visibility = View.GONE
//            initDataArrayList()
//        }
    }

    fun initDataArrayList(){
        presenter.onGetTopHeadline("in", "general")
//        articlesListGeneral = articlesList
//
//        presenter.onGetTopHeadline("in", "sports")
//        articlesListSport = articlesList
//
//        presenter.onGetTopHeadline("in", "science")
//        articlesListScience = articlesList
    }

    override fun displayProgressIndicator() {
        frg_gen_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        frg_gen_progressbar.visibility = View.GONE
//        frg_gen_swiperefresh.isRefreshing = false
    }

    override fun onDisplayArticles(articles: List<Articles>) {
        articlesList.clear()
        articlesList.addAll(articles)
        adapterGeneral.notifyDataSetChanged()
        adapterSport.notifyDataSetChanged()
        adapterScience.notifyDataSetChanged()
//        frg_gen_swiperefresh.isRefreshing = false
        view_text_general.visibility = View.VISIBLE
        view_text_sport.visibility = View.VISIBLE
        view_text_science.visibility = View.VISIBLE
    }

    override fun onDisplayErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
