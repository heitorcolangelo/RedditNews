package com.heitorcolangelo.redditnews.comments

import android.support.test.runner.AndroidJUnit4
import com.heitorcolangelo.redditnews.base.BaseFragmentTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentsTest: BaseFragmentTest() {

    @Test
    fun whenClickOn_moreComments_shouldStart_browser() {
        comments {
            openComments()
        } clickMoreComments {
            browserStarted()
        }
    }
}