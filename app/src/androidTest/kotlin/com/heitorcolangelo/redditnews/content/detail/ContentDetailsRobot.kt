package com.heitorcolangelo.redditnews.content.detail

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.content.details.ContentDetailsActivity
import com.heitorcolangelo.redditnews.mock.commentsResponse
import com.heitorcolangelo.redditnews.mock.contentResponse
import com.heitorcolangelo.redditnews.mock.mockCommentsResponse
import com.heitorcolangelo.repository.model.Content

fun ContentDetailsTest.contentDetails(func: ContentDetailsRobot.() -> Unit) = ContentDetailsRobot(rule).apply { func() }

class ContentDetailsRobot(private val rule: ActivityTestRule<ContentDetailsActivity>) {
    private var content: Content? = null
    fun withSelfText() {
        mockCommentsResponse("android", "123", 10, commentsResponse())
        content = contentResponse().page.results.first()
    }

    infix fun openContent(func: ContentDetailsResult.() -> Unit) {
        val intent = Intent()
        intent.putExtra("NEWS_KEY", content?.data!!)
        rule.launchActivity(intent)
        ContentDetailsResult().apply { func() }
    }
}

class ContentDetailsResult {
    fun selfTextIsVisible() {
        displayed {
            id(R.id.txtContentSelfText)
        }
    }
}