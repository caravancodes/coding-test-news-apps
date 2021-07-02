package com.frogobox.news.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
@Parcelize
data class News (

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: String? = null,

    @SerializedName("articles")
    var articles: List<ArticlesDao>

) : Parcelable