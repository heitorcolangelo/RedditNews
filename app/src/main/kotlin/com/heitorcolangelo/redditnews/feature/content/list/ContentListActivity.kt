package com.heitorcolangelo.redditnews.feature.content.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.content.details.ContentDetailsActivity
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.statemachine.ViewStateMachine
import com.heitorcolangelo.redditnews.ui.view.WarningView
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import kotlinx.android.synthetic.main.activity_content_list.*

class ContentListActivity : BaseActivity() {

    private val stateMachine = ViewStateMachine()

    private val model by lazy { ViewModelProviders.of(this).get(ContentListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)
        setSupportActionBar(toolbar)

        model.onLoadMore(this::loadContentPage)
        model.adapter.itemClickListener(this::onItemClick)

        setupStateMachine(savedInstanceState?.getBundle(STATE_MACHINE))
        setupRecyclerView(recyclerView)
        setupErrorView(errorView)
        setupSwipeContainer(swipeContainer)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(STATE_MACHINE, stateMachine.saveInstanceState())
    }

    private fun loadContentPage(page: String = "") {
        subscriptions.add(model.getNews(DEFAULT_SUBREDDIT, page).subscribe(this::onSuccess, this::onError))
    }

    private fun onSuccess(pages: Page<Content>) {
        model.adapter.addPage(pages)
        stateMachine.changeState(if (model.adapter.isEmpty()) EMPTY_STATE else SUCCESS_STATE)
        swipeContainer.isRefreshing = false
    }

    override fun onError(throwable: Throwable) {
        swipeContainer.isRefreshing = false
        if (model.adapter.isEmpty())
            stateMachine.changeState(ERROR_STATE)
        else model.adapter.failPage()
        super.onError(throwable)
    }

    private fun onItemClick(content: Content) {
        startActivity(ContentDetailsActivity.intent(this, content.data))
    }

    private fun setupSwipeContainer(swipeContainer: SwipeRefreshLayout) {
        swipeContainer.setOnRefreshListener {
            stateMachine.changeState(LOADING_STATE)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = model.adapter
    }

    private fun setupStateMachine(savedState: Bundle?) {
        stateMachine.setup(initialState = LOADING_STATE, restoreState = savedState) {
            add(SUCCESS_STATE) {
                visibles(recyclerView)
                gones(loadingView, emptyView, errorView)
            }
            add(LOADING_STATE) {
                visibles(loadingView)
                gones(recyclerView, emptyView, errorView)
                onEnter {
                    model.adapter.clearList()
                    loadContentPage()
                }
            }
            add(EMPTY_STATE) {
                visibles(emptyView)
                gones(loadingView, recyclerView, errorView)
            }
            add(ERROR_STATE) {
                visibles(errorView)
                gones(loadingView, emptyView, recyclerView)
            }
        }
    }

    private fun setupErrorView(view: WarningView) {
        view.setOnClickListener {
            stateMachine.changeState(LOADING_STATE)
        }
    }

    companion object {
        /**
         * I've set "android" as default value to attend the test specification.
         * But this can be easily changed as the app evolve
         */
        private const val DEFAULT_SUBREDDIT = "android"
    }
}
