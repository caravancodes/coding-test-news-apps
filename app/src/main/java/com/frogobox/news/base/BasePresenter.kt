package com.frogobox.news.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

abstract class BasePresenter<V: BaseContractView>(application: Application):
    LifecycleObserver {
    private var viewWeakReference: WeakReference<V>? = null
    var bound = false
    protected val context = application.applicationContext!!

    var savedState: Bundle? = null

//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    fun onLifecycleCreate() {
//        onCreate()
//    }

    open fun onCreate() {
//        Timber.d("BasePresenter onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onLifeCycleStart() {
        onStart()
    }

    open fun onStart() {
//        Timber.d("BasePresenter onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onLifeCycleStop() {
        onStop()
    }

    open fun onStop() {
//        Timber.d("BasePresenter onStop")
    }

    open fun onSaveState(bundle: Bundle) {
        this.savedState = bundle
    }

    fun bind(view: V) {
//        Timber.d("bind presenter")
        viewWeakReference = WeakReference<V>(view)
        bound = true
    }

    fun unbind() {
//        Timber.d("unbind presenter")
        bound = false
    }

    val view: V?
        get() {
            if (!bound) {
                return null
            }
            return viewWeakReference?.get()
        }

}