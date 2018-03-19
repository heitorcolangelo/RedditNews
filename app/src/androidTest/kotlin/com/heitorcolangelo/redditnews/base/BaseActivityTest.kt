package com.heitorcolangelo.redditnews.base

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.utils.doWait
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseActivityTest<T : BaseActivity>(activityClass: Class<T>, val autoLaunch: Boolean = true) {

    @Rule
    @JvmField
    val rule: ActivityTestRule<T> = if (autoLaunch) IntentsTestRule(activityClass, true, false) else ActivityTestRule(activityClass, true, false)

    @Before
    @Throws(InterruptedException::class)
    open fun baseTestSetup() {
        if (autoLaunch) launch()
        else Intents.init()
    }

    @After
    fun baseAfterTest() {
        if (!autoLaunch) Intents.release()
    }

    fun launch() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        rule.launchActivity(intent())
        doWait(300)
    }

    open fun intent(): Intent {
        return Intent()
    }
}