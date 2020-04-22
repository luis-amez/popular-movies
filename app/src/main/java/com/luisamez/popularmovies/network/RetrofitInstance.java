package com.luisamez.popularmovies.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton for retrieving an instance of Retrofit that manages the calls to The Movie DB API.
 * */
public final class RetrofitInstance {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
