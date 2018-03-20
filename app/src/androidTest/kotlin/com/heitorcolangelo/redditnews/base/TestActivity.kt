package com.heitorcolangelo.redditnews.base

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import com.heitorcolangelo.redditnews.ui.base.BaseActivity

class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frameLayout = FrameLayout(this)
        frameLayout.setBackgroundColor(Color.WHITE)
        frameLayout.id = android.R.id.list_container
        setContentView(frameLayout)
    }
}