package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import com.heitorcolangelo.repository.model.ResponseData
import io.reactivex.Observable

object ContentManager {

    private val repository = RepositoryProvider.get()

    fun loadContentPage(subreddit: String, content: String, page: String = ""): Observable<Page<Content>> {
        return repository.getContent(subreddit, content, page).map(ResponseData<Content>::page)
    }
}