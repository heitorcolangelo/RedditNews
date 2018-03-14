package com.heitorcolangelo.redditnews.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.ui.adapter.BaseAdapter
import com.heitorcolangelo.redditnews.ui.extension.loadFromUrl
import com.heitorcolangelo.repository.model.NewsData

class ItemNewsView : RelativeLayout, BaseAdapter.ViewBinder<NewsData> {

    private val title: TextView
    private val domain: TextView
    private val comments: TextView
    private val thumbnail: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.itemNewsViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val root = View.inflate(context, R.layout.view_item_news, this)
        root.setPadding(0, 10, 0, 10)
        title = findViewById(R.id.txtNewsTitle)
        domain = findViewById(R.id.txtNewsDomain)
        comments = findViewById(R.id.txtNumComments)
        thumbnail = findViewById(R.id.imgNewsThumbnail)
    }

    override fun bind(model: NewsData) {
        with(model) {
            title.text = data.title
            domain.text = data.domain
            comments.text = data.numComments
            thumbnail.loadFromUrl(data.thumbnail, R.drawable.ic_thumbnail_placeholder)
        }
    }
}