package com.heitorcolangelo.redditnews.feature.content.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.feature.content.details.ContentDetailsActivity
import com.heitorcolangelo.redditnews.manager.NewsManager
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

        if (savedInstanceState == null)
            loadContentPage()
        else
            restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle(ADAPTER_STATE, adapter.saveInstanceState())
        outState.putBundle(STATE_MACHINE, stateMachine.saveInstanceState())
        super.onSaveInstanceState(outState)
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(this@ContentListActivity)
        addItemDecoration(DividerItemDecoration(this@ContentListActivity, DividerItemDecoration.VERTICAL))
        adapter = this@ContentListActivity.adapter
    }

    private fun loadContentPage(page: String = "") {
        subscriptions.add(NewsManager.getNews(page)
            .subscribe(::onSuccess, ::onError)
        )
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

    private fun onSuccess(contentPage: Page<Content>) {
        adapter.addPage(contentPage)
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

    private fun restoreState(savedState: Bundle) {
        adapter.restoreInstanceState(savedState.getBundle(ADAPTER_STATE))
        stateMachine.changeState(SUCCESS_STATE)
    }

    private fun setupErrorView(view: WarningView) = with(view) {
        this.setOnClickListener {
            stateMachine.changeState(LOADING_STATE)
            adapter.retry()
        }
    }

    companion object {
        private const val ADAPTER_STATE = "ADAPTER_STATE"
    }
}
