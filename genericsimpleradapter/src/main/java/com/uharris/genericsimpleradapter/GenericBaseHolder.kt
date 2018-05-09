package com.uharris.genericsimpleradapter

import android.view.View

/**
 * Created by Ulises.harris on 8/9/16.
 */
abstract class GenericBaseHolder<T>(internal var convertView: View) {

    abstract fun configureItem(item: T)
}
