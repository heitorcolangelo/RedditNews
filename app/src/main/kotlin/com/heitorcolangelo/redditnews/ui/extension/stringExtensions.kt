package com.heitorcolangelo.redditnews.ui.extension

import android.os.Build
import android.text.Html

/**
 * Convert html tag text into string text using android.text.Html
 */
fun String.fromHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
    else
        Html.fromHtml(this).toString()
}