package com.uharris.genericsimpleradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * Created by Ulises.harris on 8/29/16.
 */
abstract class SpinnerAdapter<T, VH : GenericBaseHolder<T>, VD : GenericBaseHolder<T>>(context: Context, private val items: List<T>, private val layout: Int, private val dropdown: Int) : ArrayAdapter<T>(context, android.R.layout.simple_spinner_item, items) {
    private val inflater: LayoutInflater
    private var holder: GenericBaseHolder<T>? = null

    init {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    protected abstract fun onCreateView(convertView: View): VH

    protected abstract fun onCreateDropdownView(convertView: View): VD

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val item = items[position]
        val view = if (convertView == null) inflater.inflate(layout, parent, false) else convertView

        convertView = view

        holder = onCreateView(convertView)
        holder!!.configureItem(item)

        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val item = items[position]
        val view = if (convertView == null) inflater.inflate(dropdown, parent, false) else convertView

        convertView = view

        holder = onCreateDropdownView(convertView)
        holder!!.configureItem(item)

        return convertView
    }
}
