package com.heitorcolangelo.redditnews.feature.news.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.manager.NewsManager
import com.heitorcolangelo.redditnews.ui.adapter.PaginationAdapter
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.statemachine.ViewStateMachine
import com.heitorcolangelo.redditnews.ui.view.ItemNewsView
import com.heitorcolangelo.repository.model.News
import com.heitorcolangelo.repository.model.Page
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : BaseActivity(), PaginationAdapter.OnLoadMoreListener {

    private val stateMachine = ViewStateMachine()
    private val adapter = PaginationAdapter(::ItemNewsView, object : PaginationAdapter.OnLoadMoreListener {
        override fun onLoadMore(page: String) {
            this@NewsListActivity.loadNewsPage(page)
        }
    }).itemClickListener(::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        setSupportActionBar(toolbar)
        setupStateMachine(savedInstanceState)
        setupRecyclerView()

        if (savedInstanceState == null)
            loadNewsPage()
        else
            restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle(ADAPTER_STATE, adapter.saveInstanceState())
        outState.putBundle(STATE_MACHINE, stateMachine.saveInstanceState())
        super.onSaveInstanceState(outState)
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(this@NewsListActivity)
        addItemDecoration(DividerItemDecoration(this@NewsListActivity, DividerItemDecoration.VERTICAL))
        adapter = this@NewsListActivity.adapter
    }

    private fun loadNewsPage(page: String = "") {
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

    private fun onSuccess(newsPage: Page<News>) {
        adapter.addPage(newsPage)
        stateMachine.changeState(if (adapter.isEmpty()) EMPTY_STATE else SUCCESS_STATE)
    }

    override fun onError(throwable: Throwable) {
        if (adapter.isEmpty())
            stateMachine.changeState(ERROR_STATE)
        else adapter.failPage()
        super.onError(throwable)
    }

    private fun onItemClick(news: News) {
//        startActivity(newsDetailsActivity.intent(this, news))
    }

    private fun restoreState(savedState: Bundle) {
        adapter.restoreInstanceState(savedState.getBundle(ADAPTER_STATE))
        stateMachine.changeState(SUCCESS_STATE)
    }

    override fun onLoadMore(page: String) {

    }

    companion object {
        private const val ADAPTER_STATE = "ADAPTER_STATE"
    }
}
