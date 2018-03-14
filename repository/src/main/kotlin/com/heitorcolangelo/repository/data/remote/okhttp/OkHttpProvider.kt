package com.heitorcolangelo.repository.data.remote.okhttp

import android.content.Context
import com.heitorcolangelo.repository.data.remote.interceptor.errorHandler
import com.heitorcolangelo.repository.data.remote.interceptor.logging
import okhttp3.Cache
import okhttp3.OkHttpClient

internal object OkHttpProvider {

    private lateinit var cache: Cache
    private const val cacheSize = 10 * 1024 * 1024 // 10 MB

    internal fun init(context: Context) {
        cache = Cache(context.cacheDir, cacheSize.toLong())
    }

    internal fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging())
            .addInterceptor(errorHandler())
            .cache(cache)
            .build()
    }
}
