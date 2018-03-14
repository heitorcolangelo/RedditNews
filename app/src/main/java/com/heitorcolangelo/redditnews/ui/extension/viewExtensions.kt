package com.heitorcolangelo.redditnews.ui.extension

import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.StyleRes
import android.widget.ImageView
import android.widget.TextView
import com.heitorcolangelo.redditnews.ui.transformation.RoundedCornerTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

@Suppress("DEPRECATION")
fun TextView.setCustomTextAppearance(@StyleRes styleRes: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setTextAppearance(styleRes)
    else setTextAppearance(context, styleRes)
}

fun ImageView.loadFromUrl(url: String, @DrawableRes placeholder: Int = 0, @DrawableRes error: Int = placeholder, transformation: Transformation = RoundedCornerTransformation(10, 0)) {
    if (url.isEmpty()) return
    Picasso.with(context).load(url).apply {
        transform(transformation)
        fit()
        centerCrop()
        if (placeholder > 0) placeholder(placeholder)
        if (error > 0) error(error)
    }.into(this)
}