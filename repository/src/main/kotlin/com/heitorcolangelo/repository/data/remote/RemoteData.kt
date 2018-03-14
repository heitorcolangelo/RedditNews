package com.heitorcolangelo.repository.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.heitorcolangelo.repository.data.remote.okhttp.OkHttpProvider
import com.heitorcolangelo.repository.model.DateType
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal object RemoteData {

    private var instance: RedditService? = null

    fun get(url: String): RedditService {

        val schedulerFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        val okHttpClient = OkHttpProvider.okHttpClient()

        return instance ?: synchronized(this) {
            instance
                ?: RedditService
                    .build(url, GsonConverterFactory.create(gson), schedulerFactory, okHttpClient)
                    .also {
                        instance = it
                    }
        }
    }

    internal val gson = GsonBuilder()
        .registerTypeAdapter(DateType::class.java, DateTypeAdapter())
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
        .create()
}