package com.heitorcolangelo.redditnews.feature.comments

import android.content.Intent
import android.net.Uri
import com.heitorcolangelo.redditnews.manager.CommentManager
import com.heitorcolangelo.repository.BuildConfig

fun CommentManager.Companion.getUrlIntent(permalink: String) = with(Intent()) {
    action = Intent.ACTION_VIEW
    addCategory(Intent.CATEGORY_BROWSABLE)
    addCategory(Intent.CATEGORY_DEFAULT)
    data = Uri.parse("${BuildConfig.BASE_URL}$permalink")
    this
}