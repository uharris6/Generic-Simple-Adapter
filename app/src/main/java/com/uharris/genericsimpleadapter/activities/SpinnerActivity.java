package com.uharris.genericsimpleadapter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.uharris.genericsimpleadapter.R;
import com.uharris.genericsimpleadapter.entities.Movie;
import com.uharris.genericsimpleadapter.entities.Search;
import com.uharris.genericsimpleadapter.services.ServiceHelper;
import com.uharris.genericsimpleadapter.viewholders.MovieDropdownHolder;
import com.uharris.genericsimpleadapter.viewholders.MovieHolder;
import com.uharris.genericsimpleradapter.SpinnerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpinnerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerMovie)
    AppCompatSpinner spinnerMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getMovie("The Matrix");
    }

    private void getMovie(String s) {
        ServiceHelper serviceHelper = ServiceHelper.getInstance();
        Call<Search> call = serviceHelper.getMovieInterface().searchMovies(s, "a774c20a93911130f62c5a2dc340a492");
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                SpinnerAdapter<Movie, MovieDropdownHolder, MovieHolder> adapter = new
                        SpinnerAdapter<Movie, MovieDropdownHolder,
                                MovieHolder>(SpinnerActivity.this, response.body().getResults(), R
                                .layout
                                .movie_spinner_item, R.layout.movie_item) {

                            @Override
                            protected MovieDropdownHolder onCreateView(View convertView) {
                                return new MovieDropdownHolder(convertView);
                            }

                            @Override
                            protected MovieHolder onCreateDropdownView(View convertView) {
                                return new MovieHolder(convertView);
                            }
                        };
                spinnerMovie.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Toast.makeText(SpinnerActivity.this, t.getLocalizedMessage(), Toast
                        .LENGTH_SHORT).show();
            }
        });
    }
}
