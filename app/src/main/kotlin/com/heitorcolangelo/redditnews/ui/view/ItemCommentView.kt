package com.heitorcolangelo.redditnews.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.ui.adapter.BaseAdapter
import com.heitorcolangelo.repository.model.Comment

class ItemCommentView: LinearLayout, BaseAdapter.ViewBinder<Comment> {

    private val commentAuthor: TextView
    private val commentBody: TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.itemCommentViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    init {
        View.inflate(context, R.layout.view_item_comment, this)
        orientation = VERTICAL
        commentAuthor = findViewById(R.id.txtCommentAuthor)
        commentBody = findViewById(R.id.txtCommentBody)
    }

    override fun bind(model: Comment) {
        commentAuthor.text = model.data.author
        commentBody.text = model.data.body
    }
}