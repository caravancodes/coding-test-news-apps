package com.frogobox.frogoboxnews.network.api.call

import com.frogobox.frogoboxnews.model.Articles
import com.frogobox.frogoboxnews.model.News
import com.frogobox.frogoboxnews.model.toArticles
import com.frogobox.frogoboxnews.network.api.response.ApiResponse
import com.frogobox.frogoboxnews.network.api.service.ApiService
import com.frogobox.frogoboxnews.network.bridge.ApiClient
import com.frogobox.frogoboxnews.view.interfaces.repository.ArticlesView
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

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

class ArticleApiServiceCall(): ApiResponse {

    val newsDbApi = ApiClient.createService(ApiService::class.java)

    override suspend fun getTopHeadline(country: String, category: String): List<Articles> {
        try {
            val result = newsDbApi.getTopHeadline(country, category).await()
            return result.articles.map {
                it.toArticles()
            }
        }
        catch (e: Exception) {
            Timber.d("We have an error here")
            throw e
        }
    }

    override suspend fun getEverything(category: String, q: String): List<Articles> {
        try {
            val result = newsDbApi.getEverything(category, q).await()
            return result.articles.map {
                it.toArticles()
            }
        }
        catch (e: Exception) {
            Timber.d("We have an error here")
            throw e
        }
    }
}