package com.heitorcolangelo.repository.data.remote

import com.heitorcolangelo.repository.model.NewsResponseData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("r/android/new/.json")
    fun getNews(@Query("after") after: String = ""): Observable<NewsResponseData>

    companion object {
        internal fun build(url: String, gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, okHttpClient: OkHttpClient): RedditService {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build()
                .create(RedditService::class.java)
        }
    }
}