package com.frogobox.frogoboxnews.view.interfaces.contract

import com.frogobox.frogoboxnews.base.BaseContractPresenter
import com.frogobox.frogoboxnews.base.BaseContractView
import com.frogobox.frogoboxnews.model.Articles

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

interface ArticlesContract {
    interface View: BaseContractView {
        fun displayProgressIndicator()
        fun hideProgressIndicator()
        fun onDisplayArticles(articles: List<Articles>)
        fun onDisplayErrorMessage(message: String)
    }

    interface TopHeadlinePresenter: BaseContractPresenter<View> {
        fun setupView()
        fun onGetTopHeadline()
    }

    interface EverythingPresenter: BaseContractPresenter<View> {
        fun setupView()
        fun onGetEverything()
    }

}