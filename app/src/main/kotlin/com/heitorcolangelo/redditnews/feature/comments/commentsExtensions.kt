package com.heitorcolangelo.redditnews.feature.comments

import android.net.Uri
import com.heitorcolangelo.redditnews.manager.CommentManager
import com.heitorcolangelo.repository.BuildConfig

fun CommentManager.Companion.getUrl(permalink: String): Uri = Uri.parse("${BuildConfig.BASE_URL}$permalink")