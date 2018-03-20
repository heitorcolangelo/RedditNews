package com.heitorcolangelo.redditnews.content.detail

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.notDisplayed
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
        content = contentResponse(selfText = true).page.results.first()
    }

    fun withoutSelfText() {
        content = contentResponse(selfText = false).page.results.first()
    }

    infix fun openContent(func: ContentDetailsResult.() -> Unit) {
        mockCommentsResponse("android", "123", 17, commentsResponse())
        val intent = Intent()
        intent.putExtra("CONTENT_KEY", content?.data!!)
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

    fun selfTextIsNotVisible() {
        notDisplayed {
            id(R.id.txtContentSelfText)
        }
    }
}