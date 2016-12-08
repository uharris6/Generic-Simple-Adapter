package com.uharris.genericsimpleradapter;

import android.view.View;

/**
 * Created by Ulises.harris on 8/9/16.
 */
public abstract class GenericBaseHolder<T> {

    View convertView;

    protected abstract void configureItem(T item);


    public GenericBaseHolder(View convertView) {
        this.convertView = convertView;

    }
}
