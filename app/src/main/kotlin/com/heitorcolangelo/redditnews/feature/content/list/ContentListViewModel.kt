package com.heitorcolangelo.redditnews.feature.content.list

import android.arch.lifecycle.ViewModel
import com.heitorcolangelo.redditnews.manager.ContentManager
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import io.reactivex.Flowable

class ContentListViewModel : ViewModel() {

    private val pages = mutableListOf<Page<Content>>()

    fun getNews(subreddit: String, page: String = ""): Flowable<List<Page<Content>>> {
        return if (pages.isEmpty())
            ContentManager.loadContentPage(subreddit, "new", page).doOnNext { pages.add(it) }.map { pages }
        else Flowable.just(pages)
    }
}