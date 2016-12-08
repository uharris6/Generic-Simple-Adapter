package com.uharris.genericsimpleadapter.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uharris.genericsimpleadapter.R;
import com.uharris.genericsimpleadapter.entities.Movie;
import com.uharris.genericsimpleradapter.GenericBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ulises.harris on 12/8/16.
 */

public class MovieHolder extends GenericBaseHolder<Movie> {

    @BindView(R.id.movie_image)
    ImageView mMovieImage;
    @BindView(R.id.movie_tile)
    TextView mMovieTitle;
    @BindView(R.id.movie_year)
    TextView mMovieYear;

    View view;

    public MovieHolder(View convertView) {
        super(convertView);
        this.view = convertView;
        ButterKnife.bind(this, convertView);
    }

    @Override
    protected void configureItem(Movie item) {
        Glide.with(view.getContext()).load(item.getPoster()).into(mMovieImage);
        mMovieTitle.setText(item.getTitle());
        mMovieYear.setText(item.getYear());
    }
}
