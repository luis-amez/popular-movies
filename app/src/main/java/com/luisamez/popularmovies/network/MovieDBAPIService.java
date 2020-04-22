package com.luisamez.popularmovies.network;

import com.luisamez.popularmovies.model.APICallResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Endpoints for The Movie DB API calls.
 * */
public interface MovieDBAPIService {

    @GET("popular")
    Call<APICallResults> getPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<APICallResults> getTopRatedMovies(@Query("api_key") String apiKey);
}
