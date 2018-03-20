package com.heitorcolangelo.redditnews.content.detail

import android.support.test.runner.AndroidJUnit4
import com.heitorcolangelo.redditnews.base.BaseActivityTest
import com.heitorcolangelo.redditnews.feature.content.details.ContentDetailsActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContentDetailsTest : BaseActivityTest<ContentDetailsActivity>(ContentDetailsActivity::class.java, autoLaunch = false){

    @Test
    fun whenHasSelfText_selfTextView_shouldBeVisible() {
        contentDetails {
            withSelfText()
        } openContent {
            selfTextIsVisible()
        }
    }

    @Test
    fun whenSelfText_isEmpty_selfTextView_shouldNot_beVisible() {
        contentDetails {
            withoutSelfText()
        } openContent {
            selfTextIsNotVisible()
        }
    }


}