package com.heitorcolangelo.redditnews

import android.app.Application
import com.heitorcolangelo.repository.RepositoryProvider
import timber.log.Timber

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        RepositoryProvider.init(this)
    }
}