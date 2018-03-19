package com.heitorcolangelo.redditnews.feature.content.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.content.details.ContentDetailsActivity
import com.heitorcolangelo.redditnews.ui.adapter.PaginationAdapter
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.statemachine.ViewStateMachine
import com.heitorcolangelo.redditnews.ui.view.ItemContentView
import com.heitorcolangelo.redditnews.ui.view.WarningView
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import kotlinx.android.synthetic.main.activity_content_list.*

class ContentListActivity : BaseActivity() {

    private val stateMachine = ViewStateMachine()
    private val adapter = PaginationAdapter(::ItemContentView, object : PaginationAdapter.OnLoadMoreListener {
        override fun onLoadMore(page: String) {
            this@ContentListActivity.loadContentPage(page)
        }
    }).itemClickListener(::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)
        setSupportActionBar(toolbar)

        setupStateMachine(savedInstanceState)
        setupRecyclerView()
        setupErrorView(errorView)
        loadContentPage()
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(this@ContentListActivity)
        addItemDecoration(DividerItemDecoration(this@ContentListActivity, DividerItemDecoration.VERTICAL))
        adapter = this@ContentListActivity.adapter
    }

    private fun loadContentPage(page: String = "") {
        val model = ViewModelProviders.of(this).get(ContentListViewModel::class.java)
        subscriptions.add(
            model.getNews(DEFAULT_SUBREDDIT, page)
                .subscribe(::onSuccess, ::onError))
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

    private fun onSuccess(pages: List<Page<Content>>) {
        adapter.addPages(pages)
        stateMachine.changeState(if (adapter.isEmpty()) EMPTY_STATE else SUCCESS_STATE)
    }

    override fun onError(throwable: Throwable) {
        if (adapter.isEmpty())
            stateMachine.changeState(ERROR_STATE)
        else adapter.failPage()
        super.onError(throwable)
    }

    private fun onItemClick(content: Content) {
        startActivity(ContentDetailsActivity.intent(this, content.data))
    }

    private fun setupErrorView(view: WarningView) = with(view) {
        this.setOnClickListener {
            stateMachine.changeState(LOADING_STATE)
            adapter.retry()
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
