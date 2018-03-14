package com.heitorcolangelo.redditnews.ui.adapter

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class BaseAdapter<T : Parcelable>(private val viewCreator: (context: Context) -> ViewBinder<T>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    private var itemClickListener: ((T) -> Unit)? = null
    private var layoutInflater: LayoutInflater? = null
    private var items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinder = viewCreator.invoke(parent.context)
        val view = viewBinder as? View
            ?: throw IllegalStateException("The ViewBinder instance also must be a View")
        return ViewHolder(view)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        val view = holder.itemView
        val binder = (view as? ViewBinder<T>)
            ?: throw IllegalStateException("${holder.itemView::class} cannot be cast to ViewBinder<>")

        binder.bind(model)
        view.rootView.setOnClickListener {
            itemClickListener?.invoke(model)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun restoreInstanceState(bundle: Bundle?) {
        items = bundle?.getParcelableArrayList(STATE_LIST)!!
    }

    open fun saveInstanceState(): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArrayList(STATE_LIST, ArrayList(items))
        return bundle
    }

    fun itemClickListener(listener: ((T) -> Unit)): BaseAdapter<T> {
        this.itemClickListener = listener
        return this
    }

    // region List handle methods
    fun addNewItems(newItems: List<T>) {
        val start = itemCount
        this.items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }

    fun isEmpty() = items.isEmpty()

    fun clearList() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    internal fun getItems(): List<T> {
        return items
    }
    // endregion

    private fun getLayoutInflater(parent: ViewGroup): LayoutInflater {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        return layoutInflater!!
    }

    companion object {
        private const val STATE_LIST = "STATE_LIST"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ViewBinder<in T> {
        fun bind(model: T)
    }
}
