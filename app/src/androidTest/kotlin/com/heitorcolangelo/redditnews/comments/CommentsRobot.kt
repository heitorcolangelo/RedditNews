package com.heitorcolangelo.redditnews.comments

import android.content.Intent
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.matchIntent
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.comments.CommentsFragment
import com.heitorcolangelo.redditnews.mock.commentsResponse
import com.heitorcolangelo.redditnews.mock.mockCommentsResponse
import com.heitorcolangelo.repository.BuildConfig

fun CommentsTest.comments(func: CommentsRobot.() -> Unit) = CommentsRobot(this).apply { func() }

class CommentsRobot(private val fragment: CommentsTest) {

    fun openComments() {
        mockCommentsResponse("android", "123", 17, commentsResponse())
        fragment.initFragment(
            CommentsFragment.newInstance("android", "123", 17, "permalink")
        )
    }

    infix fun clickMoreComments(func: CommentsResult.() -> Unit) {
        matchIntent {
            action(Intent.ACTION_VIEW)
            result { ok() }
        }

        click {
            id(R.id.txtMoreComments)
        }

        CommentsResult().apply { func() }
    }
}

class CommentsResult {
    fun browserStarted() {
        matchIntent {
            action(Intent.ACTION_VIEW)
            url(BuildConfig.BASE_URL+"permalink")
        }
    }
}