package com.heitorcolangelo.redditnews.ui.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.heitorcolangelo.redditnews.ui.base.BaseActivity

fun BaseActivity.findById(@IdRes id: Int): Fragment? = supportFragmentManager.findFragmentById(id)

fun BaseActivity.findByTag(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)
