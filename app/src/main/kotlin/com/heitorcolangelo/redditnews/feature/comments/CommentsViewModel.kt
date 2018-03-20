package com.heitorcolangelo.redditnews.feature.comments

import android.arch.lifecycle.ViewModel
import com.heitorcolangelo.redditnews.manager.CommentManager
import com.heitorcolangelo.redditnews.ui.adapter.BaseAdapter
import com.heitorcolangelo.redditnews.ui.view.ItemCommentView
import com.heitorcolangelo.repository.model.Comment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

class CommentsViewModel : ViewModel() {
    val adapter = BaseAdapter(::ItemCommentView)

    fun getComments(subreddit: String, contentId: String, limit: Int): Observable<List<Comment>> {
        return CommentManager.getComments(subreddit, contentId, limit).observeOn(mainThread())
    }
}