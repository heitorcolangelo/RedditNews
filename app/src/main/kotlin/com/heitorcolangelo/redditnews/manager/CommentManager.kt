package com.heitorcolangelo.redditnews.manager

import com.heitorcolangelo.repository.RepositoryProvider
import com.heitorcolangelo.repository.model.Comment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

object CommentManager {

    private val repository = RepositoryProvider.get()

    fun getComments(subreddit: String, contentId: String, limit: Int): Observable<List<Comment>> {
        return repository
            .getComments(subreddit, contentId, limit)
            .map {
                it[COMMENTS_PAGE].page.results
            }.observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * I've set this constant because the API returns a List of ResponseData, it's a little bit weird
     * but I've made this for simplicity purpose. A better solution should be studied with more time.
     */
    private const val COMMENTS_PAGE = 1
}