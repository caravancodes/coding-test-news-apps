package com.frogobox.frogoboxnews.network.api.call

import com.frogobox.frogoboxnews.model.News
import com.frogobox.frogoboxnews.network.api.response.ApiResponse
import com.frogobox.frogoboxnews.network.api.service.ApiService
import com.frogobox.frogoboxnews.network.bridge.ApiClient
import com.frogobox.frogoboxnews.view.interfaces.repository.ArticlesView
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * FrogoBoxNews
 * Copyright (C) 16/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */

class ArticleApiServiceCall(var articlesResult : ArticlesView): ApiResponse {

    val NewsDbApi = ApiClient.createService(ApiService::class.java)

    override fun getTopHeadline(country: String, category: String) {

        val result = NewsDbApi.getTopHeadline(country, category)
        result.enqueue(object : retrofit2.Callback<News> {

            override fun onFailure(call: Call<News>, t: Throwable) {
                articlesResult.onFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    articlesResult.onGetArticles(response.body()!!.articles)
                }
            }
        })

    }

    override fun getEverything(category: String, q: String){

        val result = NewsDbApi.getEverything(category, q)
        result.enqueue(object : retrofit2.Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                articlesResult.onFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                articlesResult.onGetArticles(response.body()!!.articles)
            }
        })

    }
}