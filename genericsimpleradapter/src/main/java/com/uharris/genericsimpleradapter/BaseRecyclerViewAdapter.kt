package com.uharris.genericsimpleradapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

/**
 * Created by Ulises.harris on 8/5/16.
 */
abstract class BaseRecyclerViewAdapter<T, VH : BaseRecyclerViewHolder<T>>(private val context:
                                                                          Context) : RecyclerView.Adapter<VH>() {

    private val mLayoutInflater: LayoutInflater
    protected var list: MutableList<T> = ArrayList()
    protected var listener: Listener<T>? = null

    var all: List<T>?
        get() = list
        set(data) {
            if (data != null) {
                clear()
                list.addAll(data)
                notifyDataSetChanged()
            }
        }

    init {
        mLayoutInflater = context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    protected abstract fun onCreateItemView(inflater: LayoutInflater, parent: ViewGroup): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateItemView(mLayoutInflater, parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val viewHolder = holder as BaseRecyclerViewHolder<T>
        val t = list[position]
        viewHolder.configureItem(t)

        val view = viewHolder.itemView

        if (listener != null)
            view.setOnClickListener { listener!!.onClickItem(t, view, viewHolder.adapterPosition) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(listener: Listener<T>) {
        this.listener = listener
    }

    fun add(item: T) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun addAll(data: List<T>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
    }

    interface Listener<T> {
        fun onClickItem(t: T, v: View, position: Int)
    }
}
