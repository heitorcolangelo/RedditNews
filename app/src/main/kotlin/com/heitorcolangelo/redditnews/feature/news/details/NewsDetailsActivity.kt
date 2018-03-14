package com.heitorcolangelo.redditnews.feature.news.details

import android.content.Context
import android.content.Intent
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.repository.model.NewsData

class NewsDetailsActivity : BaseActivity() {

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