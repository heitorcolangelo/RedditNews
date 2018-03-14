package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.News
import com.heitorcolangelo.repository.model.Page
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

object NewsManager {

    fun getNews(page: String = ""): Observable<Page<News>> {
        return RepositoryProvider.get()
            .getNews(page)
            .map {
                it.page
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}