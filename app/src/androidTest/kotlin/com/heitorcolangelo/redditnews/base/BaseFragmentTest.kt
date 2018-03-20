package com.heitorcolangelo.redditnews.base

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import br.com.concretesolutions.kappuccino.utils.doWait
import org.junit.Assert

abstract class BaseFragmentTest(launch: Boolean = true) : BaseActivityTest<TestActivity>(TestActivity::class.java, launch) {

    fun initFragment(fragment: Fragment) {
        rule.activity.runOnUiThread {
            try {
                //Instantiate and insert the fragment into the container layout
                val manager = rule.activity.supportFragmentManager
                if (fragment is DialogFragment) {
                    fragment.show(manager, "TAG")
                } else {
                    val transaction = manager.beginTransaction()
                    transaction.replace(android.R.id.content, fragment)
                    transaction.commit()
                }
            } catch (e: InstantiationException) {
                Assert.fail(String.format("Could not insert %s into TestActivity: %s",
                        fragment.javaClass.simpleName,
                        e.message))
            } catch (e: IllegalAccessException) {
                Assert.fail(String.format("Could not insert %s into TestActivity: %s",
                        fragment.javaClass.simpleName, e.message))
            }
        }
        doWait(1000)
    }
}