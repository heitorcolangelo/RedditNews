package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

object NewsManager {

    /**
     * Using "android" and "news" as default values, but this can be implemented as a new feature in
     * a future.
     */
    fun getNews(page: String = ""): Observable<Page<Content>> {
        return RepositoryProvider.get()
            .getContent("android", "new", page)
            .map {
                it.page
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}