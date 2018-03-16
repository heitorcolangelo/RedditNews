package com.heitorcolangelo.redditnews.ui.base

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected val subscriptions = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }

    override fun onDetach() {
        super.onDetach()
        subscriptions.clear()
    }

    protected open fun onError(throwable: Throwable) {
        (activity as BaseActivity).onError(throwable)
    }
}