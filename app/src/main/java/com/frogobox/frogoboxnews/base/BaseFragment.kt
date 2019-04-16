package com.frogobox.frogoboxnews.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver

abstract class BaseFragment<T, V>: Fragment() where V: BaseContractView, T : BaseContractPresenter<V> {
    private var presenterInstance: T? = null

    abstract fun createPresenter(): T

    private var notifyOnCreate = true

    val presenter: T
        get()  {
            return presenterInstance!!
        }

    private fun instantiatePresenter() {
        if (presenterInstance == null) {
            presenterInstance = createPresenter()
        }
    }


    @Suppress("unchecked_cast")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        instantiatePresenter()
        presenterInstance?.bind(this as V)
        lifecycle.addObserver(presenter as LifecycleObserver)
        notifyOnCreate = true
    }

    @Suppress("unchecked_cast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (presenterInstance as BasePresenter<*>).savedState = savedInstanceState
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (notifyOnCreate) {
            notifyOnCreate = false
            (presenterInstance as BasePresenter<*>).onCreate()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        (presenterInstance as BasePresenter<*>).savedState = outState
        (presenterInstance as BasePresenter<*>).onSaveState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        presenterInstance?.unbind()
    }

}