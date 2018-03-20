package com.heitorcolangelo.redditnews.feature.comments

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.res.ResourcesCompat
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.manager.CommentManager
import com.heitorcolangelo.repository.BuildConfig

fun CommentManager.getUrl(permalink: String): Uri = Uri.parse("${BuildConfig.BASE_URL}$permalink")

fun CommentsFragment.moreCommentsIntent() = with(CustomTabsIntent.Builder()) {
    this@moreCommentsIntent.context?.let {
        setToolbarColor(ResourcesCompat.getColor(it.resources, R.color.colorPrimary, it.theme))
        setStartAnimations(it, R.anim.slide_in_right, R.anim.slide_out_left)
        setExitAnimations(it, R.anim.slide_in_left, R.anim.slide_out_right)
    }
    build()
}