package com.frogobox.news.source

import com.frogobox.news.source.ApiUrl.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
object ApiClient {
    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder()
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    fun <T> createService(serviceClass:  Class<T>): T {
        return retrofit
            .create(serviceClass)
    }
}