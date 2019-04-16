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
import org.jetbrains.anko.support.v4.startActivity

class TopHeadlineGeneralFragment : BaseFragment<ArticlesContract.TopHeadlinePresenter, ArticlesContract.View>(),
    ArticlesContract.View {

    private var articlesList: MutableList<Articles> = mutableListOf()
    private lateinit var adapter: RecyclerViewAdapter

    override fun createPresenter(): ArticlesContract.TopHeadlinePresenter {
        return TopHeadlineArticlesPresenter(activity!!.application, "in", "general")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_headline_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerViewAdapter(context, articlesList){
            startActivity<NewsDetailActivity>(NewsDetailActivity.EXTRA_ARTICLES to it)
        }
        frg_gen_recyclerview.adapter = adapter
        frg_gen_recyclerview.layoutManager = LinearLayoutManager(context)
    }

    override fun displayProgressIndicator() {
        frg_gen_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        frg_gen_progressbar.visibility = View.GONE
    }

    override fun onDisplayArticles(articles: List<Articles>) {
        articlesList.clear()
        articlesList.addAll(articles)

    }

    override fun onDisplayErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
