package com.uharris.genericsimpleadapter.services;

import com.uharris.genericsimpleadapter.entities.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ulises.harris on 12/8/16.
 */

public interface MovieService {

    @GET("search/movie")
    Call<Search> searchMovies(@Query("query") String search , @Query("api_key") String apiKey);
}
