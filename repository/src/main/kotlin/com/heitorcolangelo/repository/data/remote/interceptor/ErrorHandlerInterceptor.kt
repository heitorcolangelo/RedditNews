package com.heitorcolangelo.repository.data.remote.interceptor

import com.heitorcolangelo.repository.data.remote.exception.handleException
import okhttp3.Interceptor
import okhttp3.Response

internal class ErrorHandlerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)
            val code = response.code()
            if (response.isSuccessful) return response
            throw handleException(code)
        } catch (exception: Throwable) {
            throw handleException(0)
        }
    }
}