package com.heitorcolangelo.redditnews.feature.content.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View.VISIBLE
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.comments.CommentsFragment
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.extension.attachNewFragment
import com.heitorcolangelo.redditnews.ui.extension.fromHtml
import com.heitorcolangelo.redditnews.ui.extension.loadFromUrl
import com.heitorcolangelo.repository.model.ContentData
import kotlinx.android.synthetic.main.activity_content_details.*
import kotlinx.android.synthetic.main.view_details_content.*

class ContentDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_details)
        setupToolbar(toolbar)
        with(getContentData(intent)) {
            bindDataToLayout(this)
            setupComments(subreddit, id, numComments.toInt(), permalink)
        }
    }

    private fun bindDataToLayout(data: ContentData) = with(data) {
        txtContentTitle.text = title
        if (!selfText.isEmpty()) {
            txtContentSelfText.visibility = VISIBLE
            txtContentSelfText.text = selfText.fromHtml()
        }
        imageUrl()?.let {
            imgContentBackdrop.loadFromUrl(it.fromHtml())
        } ?: appBarLayout.setExpanded(false)
    }

    private fun setupComments(subreddit: String, contentId: String, numOfComments: Int, permalink: String) {
        val fragment = CommentsFragment.newInstance(subreddit, contentId, numOfComments, permalink)

        supportFragmentManager.beginTransaction().apply {
            attachNewFragment(this@ContentDetailsActivity, R.id.comments_fragment_container, fragment, CommentsFragment.TAG)
        }.commit()
    }

    private fun setupToolbar(toolbar: Toolbar) = with(toolbar) {
        setSupportActionBar(this)
        setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        private const val NEWS_KEY = "NEWS_KEY"

        private fun getContentData(intent: Intent) =
            intent.getParcelableExtra(NEWS_KEY) as ContentData

        fun intent(context: Context, contentData: ContentData): Intent {
            val intent = Intent(context, ContentDetailsActivity::class.java)
            intent.putExtra(NEWS_KEY, contentData)
            return intent
        }
    }
}