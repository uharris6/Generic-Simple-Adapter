package com.uharris.genericsimpleradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import java.util.ArrayList

/**
 * Created by Ulises.harris on 8/9/16.
 */
abstract class GenericBaseAdapter<T, VH : GenericBaseHolder<T>>(private val context: Context, private val layout: Int) : BaseAdapter() {
    private val mLayoutInflater: LayoutInflater
    protected var list: MutableList<T> = ArrayList()
    protected var listener: Listener<T>? = null
    private var holder: GenericBaseHolder<T>? = null

    var all: List<T>
        get() = list
        set(data) {
            clear()
            list.addAll(data)
            notifyDataSetChanged()
        }

    init {
        mLayoutInflater = context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): T {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    protected abstract fun onCreateItemView(convertView: View?): VH

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val item = list[position]
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(layout, parent, false)
            holder = onCreateItemView(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as GenericBaseHolder<T>
        }

        holder!!.configureItem(item)

        val finalConvertView = convertView
        convertView.setOnClickListener { listener!!.onClickItem(item, finalConvertView, position) }

        return convertView
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
