package com.uharris.genericsimpleadapter.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ulises.harris on 12/8/16.
 */

public class Movie {

    int id;
    String title;
    @SerializedName("poster_path")
    String poster;
    String overview;
    @SerializedName("release_date")
    String release;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = "https://image.tmdb.org/t/p/w500" + poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
