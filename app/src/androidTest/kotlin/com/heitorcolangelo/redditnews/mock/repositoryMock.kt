package com.heitorcolangelo.redditnews.mock

import com.heitorcolangelo.redditnews.manager.CommentManager
import com.heitorcolangelo.redditnews.manager.ContentManager
import com.heitorcolangelo.repository.data.remote.RedditService
import com.heitorcolangelo.repository.model.Comment
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.ResponseData
import io.reactivex.Observable
import net.vidageek.mirror.dsl.Mirror
import org.mockito.Mockito

fun mockContentResponse(subreddit: String, content: String, after: String, response: ResponseData<Content>) {
    val serviceMock: RedditService = Mockito.mock(RedditService::class.java)
    Mockito
        .`when`(serviceMock.getContent(subreddit, content, after))
        .thenReturn(Observable.just(response))
    Mirror().on(ContentManager).set().field("repository").withValue(serviceMock)
}

fun mockCommentsResponse(subreddit: String, id: String, limit: Int, response: List<ResponseData<Comment>>) {
    val serviceMock: RedditService = Mockito.mock(RedditService::class.java)
    Mockito
        .`when`(serviceMock.getComments(subreddit, id, limit))
        .thenReturn(Observable.just(response))
    Mirror().on(CommentManager).set().field("repository").withValue(serviceMock)
}