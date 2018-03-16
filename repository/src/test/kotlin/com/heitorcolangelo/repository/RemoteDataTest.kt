package com.heitorcolangelo.repository

import br.com.concretesolutions.requestmatcher.model.HttpMethod
import com.heitorcolangelo.repository.base.BaseRepositoryTest
import org.junit.Test

class RemoteDataTest : BaseRepositoryTest() {

    @Test
    fun contentRequest_shouldBeGet() {
        server.addFixture(200, "content_success.json")
            .ifRequestMatches()
            .methodIs(HttpMethod.GET)

        remoteData.getContent("android", "new").blockingFirst()
    }

    @Test
    fun commentsRequest_shouldBeGet() {
        server.addFixture(200, "comments_success.json")
            .ifRequestMatches()
            .methodIs(HttpMethod.GET)

        remoteData.getComments("android", "new", 20).blockingFirst()
    }

    @Test
    fun commentsRequest_shouldContainQuery() {
        server.addFixture(200, "comments_success.json")
            .ifRequestMatches()
            .queriesContain("limit", "20")

        remoteData.getComments("android", "123456", 20).blockingFirst()
    }

    @Test
    fun commentsRequest_pathIsCorrect() {
        server.addFixture(200, "comments_success.json")
            .ifRequestMatches()
            .pathIs("/r/android/comments/123456/.json")

        remoteData.getComments("android", "123456", 20).blockingFirst()
    }

    @Test
    fun androidNewsRequest_pathIsCorrect() {
        server.addFixture(200, "content_success.json")
            .ifRequestMatches()
            .pathIs("/r/android/new/.json")

        remoteData.getContent("android", "new").blockingFirst()
    }
}