package com.frogobox.news.source

import com.frogobox.news.source.ApiUrl.API_KEY
import com.frogobox.news.source.ApiUrl.QUERY_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val httpUrl = original.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        val request = original.newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}