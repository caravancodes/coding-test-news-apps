package com.frogobox.frogoboxnews.network.bridge

import com.frogobox.frogoboxnews.BuildConfig

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * FrogoBoxNews
 * Copyright (C) 15/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
object ApiUrl {


    const val API_KEY = BuildConfig.NEWS_API_KEY
    const val BASE_URL = BuildConfig.NEWS_BASE_URL
    const val API_VERSION = BuildConfig.NEWS_API_VERSION
    // --------------------------------------------------------------------------------
    const val VAR_TOP_HEADLINE = "top-headlines"
    const val VAR_EVERYTHING = "everything"
    const val VAR_SOURCES = "sources"
    // --------------------------------------------------------------------------------
    const val PATH_TOP_HEADLINE = "/"+ VAR_TOP_HEADLINE
    const val PATH_EVERYTHING = "/"+ VAR_EVERYTHING
    const val PATH_SOURCES = "/"+ VAR_SOURCES
    // --------------------------------------------------------------------------------
    const val URL_TOP_HEADLINE = API_VERSION + PATH_TOP_HEADLINE
    const val URL_EVERYTHING = API_VERSION + PATH_EVERYTHING
    const val URL_SOURCES = API_VERSION + PATH_SOURCES
    // --------------------------------------------------------------------------------
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_COUNRTY = "country"
    const val QUERY_CATEGORY = "category"
    const val QUERY_SOURCES = "sources"
    const val QUERY_SORT_BY = "sortBy"
    const val QUERY_LANGUAGE = "language"
    const val QUERY_SERACH = "q"




}