package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.Comment
import com.heitorcolangelo.repository.model.Page
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class CommentManager private constructor() {
    companion object {
        fun getComments(subreddit: String, contentId: String, limit: Int): Observable<Page<Comment>> {
            return RepositoryProvider.get()
                .getComments(subreddit, contentId, limit)
                .map {
                    it[1].page
                }.observeOn(AndroidSchedulers.mainThread())
        }
    }
}