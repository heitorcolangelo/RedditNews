package com.heitorcolangelo.redditnews.content.list

import android.support.test.runner.AndroidJUnit4
import com.heitorcolangelo.redditnews.base.BaseActivityTest
import com.heitorcolangelo.redditnews.feature.content.list.ContentListActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContentListTest : BaseActivityTest<ContentListActivity>(ContentListActivity::class.java, autoLaunch = false) {

    @Test
    fun whenListIsEmpty_shouldDisplayEmptyView() {
        contentList {
            withEmptyList()
        } openList {
            emptyViewIsVisible()
        }
    }

    @Test
    fun whenListIs_notEmpty_shouldDisplayListView() {
        contentList {
            notEmptyList()
        } openList {
            listViewIsVisible()
        }
    }
}