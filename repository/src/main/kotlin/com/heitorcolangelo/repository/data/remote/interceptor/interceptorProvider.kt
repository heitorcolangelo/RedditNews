package com.heitorcolangelo.repository.data.remote.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import timber.log.Timber

internal fun logging(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor {
        Timber.i(it)
    }.setLevel(BODY)
}

internal fun errorHandler() = ErrorHandlerInterceptor()
