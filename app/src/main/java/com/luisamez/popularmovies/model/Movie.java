package com.luisamez.popularmovies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model to store the relevant fields of the movie JSON object from The Movie DB API.
 * */
public final class Movie {

    @SerializedName("original_title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    private Float voteAverage;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("release_date")
    private String releaseDate;

    public Movie(String title, String posterPath, Float voteAverage, String synopsis, String releaseDate) {
        this.title = title;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
