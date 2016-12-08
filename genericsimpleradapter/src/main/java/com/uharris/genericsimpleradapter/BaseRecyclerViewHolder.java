package com.uharris.genericsimpleradapter;

/**
 * Created by Ulises.harris on 8/5/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder{

        protected abstract void configureItem(T item);

        public BaseRecyclerViewHolder(View itemView) {
            super(itemView);
        }
}
