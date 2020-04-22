package com.luisamez.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class APICallResults {

    @SerializedName("results")
    private List<Movie> movies;

    public APICallResults(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
