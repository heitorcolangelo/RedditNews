package com.heitorcolangelo.redditnews.feature.news.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.extension.fromHtml
import com.heitorcolangelo.redditnews.ui.extension.loadFromUrl
import com.heitorcolangelo.repository.model.NewsData
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.view_news_details_content.*

class NewsDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setupToolbar(toolbar)
        bindDataToLayout(getNewsData(intent))
    }

    private fun bindDataToLayout(data: NewsData) = with(data) {
        txtNewsTitle.text = title
        txtNewsSelfText.text = selfText.fromHtml()
        imageUrl()?.let {
            val url = it.fromHtml()
            imgNewsBackdrop.loadFromUrl(url)
        } ?: appBarLayout.setExpanded(false)
    }

    private fun setupToolbar(toolbar: Toolbar) = with(toolbar) {
        setSupportActionBar(this)
        setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        private const val NEWS_KEY = "NEWS_KEY"

        private fun getNewsData(intent: Intent) =
            intent.getParcelableExtra(NEWS_KEY) as NewsData

        fun intent(context: Context, newsData: NewsData): Intent {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(NEWS_KEY, newsData)
            return intent
        }
    }
}