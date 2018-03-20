package com.heitorcolangelo.repository.base

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.heitorcolangelo.repository.data.remote.RedditService
import com.heitorcolangelo.repository.data.remote.RemoteData
import com.heitorcolangelo.repository.data.remote.interceptor.errorHandler
import com.heitorcolangelo.repository.data.remote.interceptor.logging
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepositoryTest {

    @JvmField
    @Rule
    val server: RequestMatcherRule = LocalTestRequestMatcherRule()
    internal lateinit var remoteData: RedditService

    @Before
    fun setup() {
        val mockedOkHttp = OkHttpClient.Builder()
                .addInterceptor(logging())
                .addInterceptor(errorHandler())
                .build()
        val url = server.url("/").toString()
        val mockedRedditService = RedditService.build(
                url,
                GsonConverterFactory.create(),
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()),
                mockedOkHttp
        )

        val mockedRemoteData = Mockito.mock(RemoteData::class.java)
        Mockito.`when`(mockedRemoteData.get(url)).thenReturn(mockedRedditService)
        remoteData = mockedRemoteData.get(url)
    }
}