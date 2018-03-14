package com.heitorcolangelo.redditnews.feature.news.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.heitorcolangelo.redditnews.R
import com.heitorcolangelo.redditnews.manager.NewsManager
import com.heitorcolangelo.redditnews.ui.adapter.BaseAdapter
import com.heitorcolangelo.redditnews.ui.base.BaseActivity
import com.heitorcolangelo.redditnews.ui.statemachine.ViewStateMachine
import com.heitorcolangelo.redditnews.ui.view.ItemNewsView
import com.heitorcolangelo.repository.model.NewsData
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : BaseActivity() {

    private val stateMachine = ViewStateMachine()
    private val adapter = BaseAdapter(::ItemNewsView).itemClickListener(::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        setSupportActionBar(toolbar)
        setupStateMachine(savedInstanceState)
        setupRecyclerView()

        if (savedInstanceState == null)
            getnewsList()
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

    private fun getnewsList() {
        subscriptions.add(NewsManager.getNews()
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

    private fun onSuccess(news: List<NewsData>) {
        adapter.addNewItems(news)
        stateMachine.changeState(if (adapter.isEmpty()) EMPTY_STATE else SUCCESS_STATE)
    }

    override fun onError(throwable: Throwable) {
        stateMachine.changeState(ERROR_STATE)
        super.onError(throwable)
    }

    private fun onItemClick(news: NewsData) {
//        startActivity(newsDetailsActivity.intent(this, news))
    }

    private fun restoreState(savedState: Bundle) {
        adapter.restoreInstanceState(savedState.getBundle(ADAPTER_STATE))
        stateMachine.changeState(SUCCESS_STATE)
    }

    companion object {
        private const val ADAPTER_STATE = "ADAPTER_STATE"
    }
}
