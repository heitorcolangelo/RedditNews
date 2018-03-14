package com.heitorcolangelo.repository

import android.content.Context
import com.heitorcolangelo.repository.data.DataProvider
import com.jakewharton.threetenabp.AndroidThreeTen

abstract class RepositoryProvider {
    companion object {
        fun get() = DataProvider.remote()

        fun init(context: Context) {
            DataProvider.init(context)
            AndroidThreeTen.init(context)
        }
    }
}