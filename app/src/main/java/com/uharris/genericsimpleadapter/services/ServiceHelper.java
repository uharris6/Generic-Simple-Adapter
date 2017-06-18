package com.uharris.genericsimpleadapter.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ulises.harris on 4/27/16.
 */
public class ServiceHelper {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static Retrofit retrofit;

    private static ServiceHelper instance = null;

    public ServiceHelper() {
        this(BASE_URL);
    }

    public static MovieService mMovieInterface;

    public ServiceHelper(String baseUrl) {

        Gson gson = new GsonBuilder()
                .create();

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mMovieInterface = retrofit.create(MovieService.class);
        }

    }

    public static ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
        }

        return instance;
    }

    public MovieService getMovieInterface() {
        return mMovieInterface;
    }


}
