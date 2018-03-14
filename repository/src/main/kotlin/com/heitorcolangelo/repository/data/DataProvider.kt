package com.heitorcolangelo.repository.data

import android.content.Context
import com.heitorcolangelo.repository.BuildConfig
import com.heitorcolangelo.repository.data.remote.RemoteData
import com.heitorcolangelo.repository.data.remote.okhttp.OkHttpProvider

abstract class DataProvider {
    companion object {
        internal fun init(context: Context) {
            OkHttpProvider.init(context)
        }

        internal fun remote() = RemoteData.get(BuildConfig.BASE_URL)

        /**
         * This is for a future implementation of local data
         **/
        internal fun local() {
            throw IllegalStateException("Not implemented yet!")
        }
    }
}