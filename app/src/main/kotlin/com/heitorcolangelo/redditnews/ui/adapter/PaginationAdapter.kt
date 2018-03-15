package com.heitorcolangelo.redditnews.ui.adapter

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.heitorcolangelo.redditnews.ui.view.ItemProgressView
import com.heitorcolangelo.repository.model.Page
import kotlinx.android.synthetic.main.view_item_progress.view.*

class PaginationAdapter<T : Parcelable>(viewCreator: (context: Context) -> ViewBinder<T>, private val listener: OnLoadMoreListener) : BaseAdapter<T>(viewCreator) {
    private var hasLoadingItem: Boolean = false
    private var hasError: Boolean = false
    private var nextPage = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType != VIEW_PROGRESS)
            super.onCreateViewHolder(parent, viewType)
        else
            ViewHolder(ItemProgressView(parent.context) as View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) != VIEW_PROGRESS)
            super.onBindViewHolder(holder, position)
        else {
            val binder = holder.itemView as ItemProgressView
            binder.rootView.tag = PROGRESS_TAG
            binder.progressBar.visibility = if (hasError) GONE else VISIBLE
            binder.txtTryAgain.visibility = if (hasError) VISIBLE else GONE
            binder.txtTryAgain.setOnClickListener({ listener.onLoadMore(nextPage) })
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.clearOnChildAttachStateChangeListeners()
        recyclerView.addOnChildAttachStateChangeListener(childAttachListener())
    }

    override fun saveInstanceState(): Bundle {
        val bundle: Bundle = super.saveInstanceState()
        bundle.putBoolean(HAS_NEXT_KEY, hasLoadingItem)
        bundle.putBoolean(HAS_ERROR_KEY, hasError)
        bundle.putString(CURRENT_PAGE_KEY, nextPage)
        return bundle
    }

    override fun restoreInstanceState(bundle: Bundle?) {
        super.restoreInstanceState(bundle)
        if (bundle == null) return
        hasLoadingItem = bundle.getBoolean(HAS_NEXT_KEY, false)
        hasError = bundle.getBoolean(HAS_ERROR_KEY, false)
        nextPage = bundle.getString(CURRENT_PAGE_KEY, "")
    }

    private fun childAttachListener(): RecyclerView.OnChildAttachStateChangeListener {
        return object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                if (view.tag != null
                    && view.tag == PROGRESS_TAG
                    && !hasError)
                    listener.onLoadMore(nextPage)
            }

            override fun onChildViewDetachedFromWindow(view: View) {}
        }
    }

    override fun getItemViewType(position: Int) =
        if (position == getItems().size) VIEW_PROGRESS else VIEW_ITEM

    override fun getItemCount() =
        super.getItemCount() + if (hasLoadingItem) 1 else 0

    override fun clearList() {
        if (hasLoadingItem) removeLoadingItem()
        super.clearList()
    }

    override fun itemClickListener(listener: (T) -> Unit): PaginationAdapter<T> {
        super.itemClickListener(listener)
        return this
    }

    fun addPage(page: Page<T>) {
        hasError = false
        if (hasLoadingItem) removeLoadingItem()
        addNewItems(page.results)
        nextPage = page.after

        if (!nextPage.isEmpty())
            insertLoadingItem()
    }

    fun failPage() {
        if (!hasLoadingItem) return
        hasError = true
        notifyItemChanged(itemCount)
    }

    fun retry() = listener.onLoadMore(nextPage)

    private fun insertLoadingItem() {
        notifyItemInserted(itemCount)
        hasLoadingItem = true
    }

    private fun removeLoadingItem() {
        hasLoadingItem = false
        notifyItemRemoved(itemCount)
    }

    interface OnLoadMoreListener {
        fun onLoadMore(page: String)
    }

    companion object {
        private const val PROGRESS_TAG = "PROGRESS_TAG"
        private const val HAS_NEXT_KEY = "HAS_NEXT_KEY"
        private const val HAS_ERROR_KEY = "HAS_ERROR_KEY"
        private const val CURRENT_PAGE_KEY = "CURRENT_PAGE_KEY"
        private const val VIEW_PROGRESS = 0
        private const val VIEW_ITEM = 1
    }
}