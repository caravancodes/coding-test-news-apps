package com.frogobox.frogoboxnews.network.api

import com.frogobox.frogoboxnews.model.News
import com.frogobox.frogoboxnews.network.bridge.ApiUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

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
interface ApiResponse {

    fun getTopHeadline(country : String, category : String)
    fun getEverything(category : String, q : String)

}