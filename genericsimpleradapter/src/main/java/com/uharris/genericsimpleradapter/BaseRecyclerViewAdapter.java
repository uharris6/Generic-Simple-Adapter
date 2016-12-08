package com.uharris.genericsimpleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ulises.harris on 8/5/16.
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<VH> {

    private LayoutInflater mLayoutInflater;
    protected List<T> list = new ArrayList<>();
    protected Listener<T> listener;

    private final Context context;

    public BaseRecyclerViewAdapter(Context context) {
        super();
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
    }

    protected abstract VH onCreateItemView(LayoutInflater inflater, ViewGroup parent);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateItemView(mLayoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final BaseRecyclerViewHolder<T> viewHolder = (BaseRecyclerViewHolder<T>) holder;
        final T t = list.get(position);
        viewHolder.configureItem(t);

        final View view = viewHolder.itemView;

        if(listener != null)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(t, view, viewHolder.getAdapterPosition());
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(Listener<T> listener){
        this.listener = listener;
    }

    public void add(T item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void setAll(List<T> data) {
        if(data != null){
            clear();
            list.addAll(data);
            notifyDataSetChanged();
        }
    }

    public List<T> getAll() {
        return list;
    }

    public void clear() {
        list.clear();
    }

    public interface Listener<T>{
        void onClickItem(T t, View v, int position);
    }
}
