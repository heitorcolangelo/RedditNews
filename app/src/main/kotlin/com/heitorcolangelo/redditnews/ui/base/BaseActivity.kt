package com.heitorcolangelo.redditnews.ui.base

import android.support.v7.app.AppCompatActivity
import com.heitorcolangelo.redditnews.BuildConfig
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    protected val subscriptions = CompositeDisposable()

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    open fun onError(throwable: Throwable) {
        if (BuildConfig.DEBUG)
            Timber.e(throwable)
    }

    companion object {
        const val SUCCESS_STATE = 0
        const val LOADING_STATE = 1
        const val EMPTY_STATE = 2
        const val ERROR_STATE = 3
        const val STATE_MACHINE = "STATE_MACHINE"
    }
}