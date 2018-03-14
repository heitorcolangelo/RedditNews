package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.NewsData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

object NewsManager {

    fun getNews(page: String = ""): Observable<List<NewsData>> {
        return RepositoryProvider.get()
            .getNews(page)
            .map {
                it.page.children
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

}