package com.heitorcolangelo.redditnews.ui.extension

import android.support.annotation.IdRes
import android.support.v4.app.FragmentTransaction
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.base.BaseFragment

fun FragmentTransaction.attachNewFragment(activity: BaseActivity, @IdRes target: Int, fragment: BaseFragment, fragmentTag: String) {
    activity.findByTag(fragmentTag)?.let(::attach)
        ?: add(target, fragment, fragmentTag)
}