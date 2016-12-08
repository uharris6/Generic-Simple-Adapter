package com.uharris.genericsimpleradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ulises.harris on 8/9/16.
 */
public abstract class GenericBaseAdapter<T, VH extends GenericBaseHolder<T>> extends BaseAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    protected List<T> list = new ArrayList<>();
    protected Listener<T> listener;
    private GenericBaseHolder<T> holder;
    private int layout;

    public GenericBaseAdapter(Context context, int layout) {
        super();
        this.context = context;
        this.layout = layout;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract VH onCreateItemView(View convertView);

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final T item = list.get(position);
        if(convertView == null){
            convertView = mLayoutInflater.inflate(layout, parent, false);
            holder = onCreateItemView(convertView);
            convertView.setTag(holder);
        }else{
            holder = (GenericBaseHolder<T>) convertView.getTag();
        }

        holder.configureItem(item);

        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickItem(item, finalConvertView, position);
            }
        });

        return convertView;
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
        clear();
        list.addAll(data);
        notifyDataSetChanged();
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
