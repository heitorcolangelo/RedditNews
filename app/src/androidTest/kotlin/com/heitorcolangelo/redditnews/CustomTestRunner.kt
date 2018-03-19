package com.heitorcolangelo.redditnews

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener
import com.linkedin.android.testbutler.TestButler

class CustomTestRunner : AndroidJUnitRunner() {
    override fun onStart() {
        TestButler.setup(InstrumentationRegistry.getTargetContext())
        super.onStart()
    }

    override fun finish(resultCode: Int, results: Bundle) {
        TestButler.teardown(InstrumentationRegistry.getTargetContext())
        super.finish(resultCode, results)
    }

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        DexOpener.builder(context)
            .buildConfig(Class.forName("com.heitorcolangelo.redditnews.BuildConfig"))
            .build()
            .installTo(cl)
        return super.newApplication(cl, className, context)
    }
}