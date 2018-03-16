package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class ContentManager {
    companion object {
        fun getNews(subreddit: String, page: String = ""): Observable<Page<Content>> {
            return RepositoryProvider.get()
                .getContent(subreddit, "new", page)
                .map {
                    it.page
                }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}