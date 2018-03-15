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
import com.heitorcolangelo.repository.model.Content

class ItemContentView : RelativeLayout, BaseAdapter.ViewBinder<Content> {

    private val title: TextView
    private val domain: TextView
    private val comments: TextView
    private val thumbnail: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.itemContentViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val root = View.inflate(context, R.layout.view_item_content, this)
        root.setPadding(0, 10, 0, 10)
        title = findViewById(R.id.txtNewsTitle)
        domain = findViewById(R.id.txtNewsDomain)
        comments = findViewById(R.id.txtNumComments)
        thumbnail = findViewById(R.id.imgNewsThumbnail)
    }

    override fun bind(model: Content) {
            title.text = model.data.title
            domain.text = model.data.domain
            comments.text = model.data.numComments
            thumbnail.loadFromUrl(model.data.thumbnail, R.drawable.ic_thumbnail_placeholder)
    }
}