package com.uharris.genericsimpleradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Ulises.harris on 8/29/16.
 */
public abstract class SpinnerAdapter<T, VH extends GenericBaseHolder<T>, VD extends GenericBaseHolder<T>> extends ArrayAdapter<T> {

    private final List<T> items;
    private final int layout;
    private final int dropdown;
    private final LayoutInflater inflater;
    private GenericBaseHolder<T> holder;

    public SpinnerAdapter(Context context, List<T> items, int layout, int dropdown) {
        super(context, android.R.layout.simple_spinner_item, items);
        this.items = items;
        this.layout = layout;
        this.dropdown = dropdown;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected abstract VH onCreateView(View convertView);

    protected abstract VD onCreateDropdownView(View convertView);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final T item = items.get(position);
        final View view = (convertView == null) ? inflater.inflate(layout, parent, false) : convertView;

        convertView = view;

        holder = onCreateView(convertView);
        holder.configureItem(item);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        final T item = items.get(position);
        final View view = (convertView == null) ? inflater.inflate(dropdown, parent, false) : convertView;

        convertView = view;

        holder = onCreateDropdownView(convertView);
        holder.configureItem(item);

        return convertView;
    }
}
