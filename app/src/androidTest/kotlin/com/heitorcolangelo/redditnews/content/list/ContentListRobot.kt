package com.heitorcolangelo.redditnews.content.list

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.notDisplayed
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.content.list.ContentListActivity
import com.heitorcolangelo.redditnews.mock.contentEmptyResponse
import com.heitorcolangelo.redditnews.mock.contentResponse
import com.heitorcolangelo.redditnews.mock.mockContentResponse

fun ContentListTest.contentList(func: ContentListRobot.() -> Unit) = ContentListRobot(rule).apply { func() }

class ContentListRobot(private val rule: ActivityTestRule<ContentListActivity>) {

    fun withEmptyList() {
        mockContentResponse("android", "new", "", contentEmptyResponse())
    }

    infix fun openList(func: ContentListResult.() -> Unit) {
        rule.launchActivity(Intent())
        ContentListResult().apply { func() }
    }

    fun notEmptyList() {
        mockContentResponse("android", "new", "", contentResponse())
    }
}

class ContentListResult {

    fun emptyViewIsVisible() {
        displayed {
            id(R.id.emptyView)
            text(R.string.default_empty_title)
            text(R.string.default_empty_subtitle)
        }
    }

    fun listViewIsVisible() {
        displayed {
            id(R.id.recyclerView)
        }

        notDisplayed {
            id(R.id.emptyView)
            id(R.id.errorView)
            id(R.id.loadingView)
        }
    }
}