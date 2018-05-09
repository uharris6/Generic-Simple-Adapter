package com.uharris.genericsimpleradapter

/**
 * Created by Ulises.harris on 8/5/16.
 */

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseRecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun configureItem(item: T)
}
