package com.frogobox.news.mvp.topheadline


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.news.R
import com.frogobox.news.base.BaseFragment
import com.frogobox.news.model.Articles
import com.frogobox.news.mvp.detail.NewsDetailActivity
import com.frogobox.news.mvp.main.RecyclerViewAdapter
import com.frogobox.news.source.ArticlesContract
import kotlinx.android.synthetic.main.fragment_top_headline_general.*

class TopHeadlineGeneralFragment : BaseFragment<ArticlesContract.TopHeadlinePresenter, ArticlesContract.View>(), ArticlesContract.View {

    private var articlesList: MutableList<Articles> = mutableListOf()
    private var articlesListGeneral: MutableList<Articles> = mutableListOf()
    private var articlesListSport: MutableList<Articles> = mutableListOf()
    private var articlesListScience: MutableList<Articles> = mutableListOf()

    private lateinit var adapterGeneral: RecyclerViewAdapter
    private lateinit var adapterSport: RecyclerViewAdapter
    private lateinit var adapterScience: RecyclerViewAdapter

    override fun createPresenter(): ArticlesContract.TopHeadlinePresenter {
        return TopHeadlineArticlesPresenter(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_headline_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataArrayList()

        adapterGeneral = RecyclerViewAdapter(R.layout.content_list_news_horizontal,context, articlesList){
            intentTo(it)
        }

        adapterSport = RecyclerViewAdapter(R.layout.content_list_news_horizontal,context, articlesList){
            intentTo(it)
        }

        adapterScience = RecyclerViewAdapter(R.layout.content_list_news_horizontal,context, articlesList){
            intentTo(it)
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

    private fun intentTo(data: Articles) {
        val intent = Intent(requireContext(), NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.EXTRA_ARTICLES, data)
        startActivity(intent)
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
