package com.luisamez.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Model to store the relevant fields of the movie JSON object from The Movie DB API.
 * */
public final class Movie implements Parcelable {

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

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel origin) {
            return new Movie(origin);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(String title, String posterPath, Float voteAverage, String synopsis, String releaseDate) {
        this.title = title;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }

    public Movie (Parcel origin) {
        readFromParcel(origin);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeFloat(voteAverage);
        dest.writeString(synopsis);
        dest.writeString(releaseDate);
    }

    private void readFromParcel(Parcel origin) {
        title = origin.readString();
        posterPath = origin.readString();
        voteAverage = origin.readFloat();
        synopsis = origin.readString();
        releaseDate = origin.readString();
    }
}
