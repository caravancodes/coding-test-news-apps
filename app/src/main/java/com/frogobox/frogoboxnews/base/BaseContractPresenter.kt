package com.frogobox.frogoboxnews.base

/**
 * Created by yogieadrisatria on 13/10/17.
 */

interface BaseContractPresenter<V: BaseContractView> {
    fun onAttach(view: V)
    fun onDetach()
}

