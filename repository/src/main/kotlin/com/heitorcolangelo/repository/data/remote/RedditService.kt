package com.heitorcolangelo.repository.data.remote

import com.heitorcolangelo.repository.model.Comment
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.ResponseData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("r/{subreddit}/{content}/.json")
    fun getContent(
        @Path("subreddit") subreddit: String,
        @Path("content") content: String,
        @Query("after") after: String = ""): Observable<ResponseData<Content>>

    @GET("r/{subreddit}/comments/{id}/.json")
    fun getComments(@Path("subreddit") subreddit: String, @Path("id") id: String): Observable<ResponseData<Comment>>

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