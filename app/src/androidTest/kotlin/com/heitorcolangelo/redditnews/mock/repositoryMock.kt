package com.heitorcolangelo.redditnews.mock

import com.heitorcolangelo.redditnews.manager.ContentManager
import com.heitorcolangelo.repository.data.remote.RedditService
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.ResponseData
import io.reactivex.Flowable
import net.vidageek.mirror.dsl.Mirror
import org.mockito.Mockito

fun mockContentResponse(subreddit: String, content: String, after: String, response: ResponseData<Content>) {
    val serviceMock: RedditService = Mockito.mock(RedditService::class.java)
    Mockito
        .`when`(serviceMock.getContent(subreddit, content, after))
        .thenReturn(Flowable.just(response))
    Mirror().on(ContentManager).set().field("repository").withValue(serviceMock)
}