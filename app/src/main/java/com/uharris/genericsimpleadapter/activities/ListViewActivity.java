package com.uharris.genericsimpleadapter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.uharris.genericsimpleadapter.R;
import com.uharris.genericsimpleadapter.entities.Movie;
import com.uharris.genericsimpleadapter.entities.Search;
import com.uharris.genericsimpleadapter.services.ServiceHelper;
import com.uharris.genericsimpleadapter.viewholders.MovieHolder;
import com.uharris.genericsimpleradapter.GenericBaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView)
    ListView listView;
    private GenericBaseAdapter<Movie, MovieHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        init();
        getMovie("The Matrix");
    }

    private void getMovie(String s) {
        ServiceHelper serviceHelper = ServiceHelper.getInstance();
        Call<Search> call = serviceHelper.getMovieInterface().searchMovies(s, "a774c20a93911130f62c5a2dc340a492");
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                adapter.setAll(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Toast.makeText(ListViewActivity.this, t.getLocalizedMessage(), Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    private void init() {

        adapter = new GenericBaseAdapter<Movie, MovieHolder>(this, R.layout.movie_item) {

            @Override
            protected MovieHolder onCreateItemView(View convertView) {
                return new MovieHolder(convertView);
            }
        };

        adapter.setOnItemClickListener(new GenericBaseAdapter.Listener<Movie>() {
            @Override
            public void onClickItem(Movie movie, View v, int position) {

            }
        });

        listView.setAdapter(adapter);
    }

}
