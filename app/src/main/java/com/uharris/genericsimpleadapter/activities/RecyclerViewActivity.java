package com.uharris.genericsimpleadapter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uharris.genericsimpleadapter.R;
import com.uharris.genericsimpleadapter.entities.Movie;
import com.uharris.genericsimpleadapter.entities.Search;
import com.uharris.genericsimpleadapter.services.ServiceHelper;
import com.uharris.genericsimpleadapter.viewholders.MovieViewHolder;
import com.uharris.genericsimpleradapter.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movies_recycler_view)
    RecyclerView moviesRecyclerView;
    private BaseRecyclerViewAdapter<Movie, MovieViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        init();
        getMovie("The Matrix");
    }

    private void getMovie(String s) {
        ServiceHelper serviceHelper = ServiceHelper.getInstance();
        Call<Search> call = serviceHelper.getMovieInterface().searchMovies(s);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                adapter.setAll(response.body().getSearch());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Toast.makeText(RecyclerViewActivity.this, t.getLocalizedMessage(), Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BaseRecyclerViewAdapter<Movie, MovieViewHolder>(this) {
            @Override
            protected MovieViewHolder onCreateItemView(LayoutInflater inflater, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                        parent,
                        false);
                return new MovieViewHolder(view, parent.getContext());
            }
        };

        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.Listener<Movie>() {
            @Override
            public void onClickItem(Movie movie, View v, int position) {

            }
        });

        moviesRecyclerView.setAdapter(adapter);
    }
}
