package com.uharris.genericsimpleadapter.viewholders;

import android.view.View;
import android.widget.TextView;

import com.uharris.genericsimpleadapter.R;
import com.uharris.genericsimpleadapter.entities.Movie;
import com.uharris.genericsimpleradapter.GenericBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ulises.harris on 12/8/16.
 */

public class MovieDropdownHolder extends GenericBaseHolder<Movie> {

    @BindView(R.id.text)
    TextView textView;

    public MovieDropdownHolder(View convertView) {
        super(convertView);
        ButterKnife.bind(this, convertView);
    }

    @Override
    protected void configureItem(Movie item) {
        textView.setText(item.getTitle());
    }
}
