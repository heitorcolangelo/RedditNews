package com.heitorcolangelo.redditnews.feature.comments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.manager.CommentManager
import com.heitorcolangelo.redditnews.ui.base.BaseActivity.Companion.EMPTY_STATE
import com.heitorcolangelo.redditnews.ui.base.BaseActivity.Companion.ERROR_STATE
import com.heitorcolangelo.redditnews.ui.base.BaseActivity.Companion.LOADING_STATE
import com.heitorcolangelo.redditnews.ui.base.BaseActivity.Companion.STATE_MACHINE
import com.heitorcolangelo.redditnews.ui.base.BaseActivity.Companion.SUCCESS_STATE
import com.heitorcolangelo.redditnews.ui.base.BaseFragment
import com.heitorcolangelo.redditnews.ui.statemachine.ViewStateMachine
import com.heitorcolangelo.repository.model.Comment
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment : BaseFragment() {

    private val stateMachine = ViewStateMachine()
    private val model by lazy { ViewModelProviders.of(activity!!).get(CommentsViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMoreCommentsView(txtMoreComments)
        setupRecyclerView(recyclerView)
        setupStateMachine(savedInstanceState?.getBundle(STATE_MACHINE))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(STATE_MACHINE, stateMachine.saveInstanceState())
    }

    private fun loadContentPage() {
        with(arguments) {
            subscriptions.add(
                model.getComments(subreddit(this), contentId(this), numOfComments(this))
                    .subscribe(::onSuccess, ::onError)
            )
        }
    }

    private fun onSuccess(comments: List<Comment>) {
        if (comments.isEmpty())
            stateMachine.changeState(EMPTY_STATE)
        else {
            stateMachine.changeState(SUCCESS_STATE)
            model.adapter.addNewItems(comments)
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        stateMachine.changeState(ERROR_STATE)
    }

    private fun setupMoreCommentsView(view: TextView) = with(view) {
        setOnClickListener {
            val intent = moreCommentsIntent()
            intent.launchUrl(activity, CommentManager.getUrl(permalink(arguments)))
        }
    }

    private fun moreCommentsIntent() = with(CustomTabsIntent.Builder()) {
        this@CommentsFragment.context?.let {
            setToolbarColor(ResourcesCompat.getColor(it.resources, R.color.colorPrimary, it.theme))
            setStartAnimations(it, R.anim.slide_in_right, R.anim.slide_out_left)
            setExitAnimations(it, R.anim.slide_in_left, R.anim.slide_out_right)
        }
        build()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) = with(recyclerView) {
        val context = this@CommentsFragment.activity
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = model.adapter
        isNestedScrollingEnabled = false
    }

    private fun setupStateMachine(savedState: Bundle?) {
        stateMachine.setup(initialState = LOADING_STATE, restoreState = savedState) {
            add(SUCCESS_STATE) {
                visibles(recyclerView, txtMoreComments)
                gones(loadingView, emptyView, errorView)
            }
            add(LOADING_STATE) {
                visibles(loadingView)
                gones(recyclerView, txtMoreComments, emptyView, errorView)
                onEnter {
                    model.adapter.clearList()
                    loadContentPage()
                }
            }
            add(EMPTY_STATE) {
                visibles(emptyView)
                gones(loadingView, recyclerView, txtMoreComments, errorView)
            }
            add(ERROR_STATE) {
                visibles(errorView)
                gones(loadingView, emptyView, recyclerView, txtMoreComments)
            }
        }
    }

    companion object {
        const val TAG = "CommentsFragment.TAG"
        private const val SUBREDDIT_KEY = "SUBREDDIT_KEY"
        private const val CONTENT_ID_KEY = "CONTENT_ID_KEY"
        private const val PERMALINK_KEY = "PERMALINK_KEY"
        private const val NUM_OF_COMMENTS_KEY = "NUM_OF_COMMENTS_KEY"

        private fun subreddit(bundle: Bundle?) = bundle?.getString(SUBREDDIT_KEY)!!

        private fun contentId(bundle: Bundle?) = bundle?.getString(CONTENT_ID_KEY)!!

        private fun numOfComments(bundle: Bundle?) = bundle?.getInt(NUM_OF_COMMENTS_KEY)!!

        private fun permalink(bundle: Bundle?) = bundle?.getString(PERMALINK_KEY)!!

        fun newInstance(subreddit: String, contentId: String, numOfComments: Int, permalink: String): CommentsFragment {
            val newFragment = CommentsFragment()
            with(Bundle()) {
                putString(SUBREDDIT_KEY, subreddit)
                putString(CONTENT_ID_KEY, contentId)
                putString(PERMALINK_KEY, permalink)
                putInt(NUM_OF_COMMENTS_KEY, numOfComments)
                newFragment.arguments = this
            }
            return newFragment
        }
    }
}