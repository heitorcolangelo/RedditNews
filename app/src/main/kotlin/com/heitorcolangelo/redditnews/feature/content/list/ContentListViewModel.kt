package com.heitorcolangelo.redditnews.feature.content.list

import android.arch.lifecycle.ViewModel
import com.heitorcolangelo.redditnews.manager.ContentManager
import com.heitorcolangelo.redditnews.ui.adapter.PaginationAdapter
import com.heitorcolangelo.redditnews.ui.view.ItemContentView
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

class ContentListViewModel : ViewModel() {

    val adapter = PaginationAdapter(::ItemContentView)

    fun getNews(subreddit: String, page: String = ""): Observable<Page<Content>> {
        return ContentManager.loadContentPage(subreddit, "new", page).observeOn(mainThread())
    }

    fun onLoadMore(func: (String) -> Unit) {
        adapter.listener = object : PaginationAdapter.OnLoadMoreListener {
            override fun onLoadMore(page: String) {
                func(page)
            }
        }
    }
}