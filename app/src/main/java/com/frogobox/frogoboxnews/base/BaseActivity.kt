package com.frogobox.frogoboxnews.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseActivity<T, V>: AppCompatActivity(), LifecycleObserver where V: BaseContractView, T : BaseContractPresenter<V> {
    private var presenterInstance: T? = null

    abstract fun createPresenter(): T

    val presenter: T
        get()  {
            return presenterInstance!!
        }

    private fun instantiatePresenter() {
        if (presenterInstance == null) {
            presenterInstance = createPresenter()
        }
    }

//    fun getPresenter(): T {
//        if (presenterInstance == null) {
//            presenterInstance = createPresenter()
//        }
//        return presenterInstance!!
//    }

    @Suppress("unchecked_cast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instantiatePresenter()
        if (presenterInstance is BasePresenter<*>) {
            presenterInstance?.onAttach(this as V)
            lifecycle.addObserver(presenter as LifecycleObserver)
            lifecycle.addObserver(this)
            (presenterInstance as BasePresenter<*>).savedState = savedInstanceState
        }
        else {
            throw IllegalArgumentException("Presenter should implement LifecycleObserver.")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun lifecycleOnCreate() {
        (presenterInstance as BasePresenter<*>).onCreate()
    }


    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        (presenterInstance as BasePresenter<*>).savedState = outState
        (presenterInstance as BasePresenter<*>).onSaveState(outState!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterInstance?.onDetach()
        lifecycle.removeObserver(presenter as LifecycleObserver)
        lifecycle.removeObserver(this)
    }
}