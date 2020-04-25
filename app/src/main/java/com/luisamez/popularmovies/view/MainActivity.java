package com.luisamez.popularmovies.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.luisamez.popularmovies.BuildConfig;
import com.luisamez.popularmovies.R;
import com.luisamez.popularmovies.controller.MoviesAdapter;
import com.luisamez.popularmovies.model.APICallResults;
import com.luisamez.popularmovies.model.Movie;
import com.luisamez.popularmovies.network.MovieDBAPIService;
import com.luisamez.popularmovies.network.RetrofitInstance;
import com.luisamez.popularmovies.view.AutofitRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AutofitRecyclerView moviesGrid;
    private MoviesAdapter adapter;
    private MovieDBAPIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = RetrofitInstance.getRetrofitInstance().create(MovieDBAPIService.class);
        showMostPopularMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_most_popular) {
            showMostPopularMovies();
            return true;
        } else if (item.getItemId() == R.id.action_top_rated) {
            showTopRatedMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMostPopularMovies() {
        Call<APICallResults> call = service.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);
        call.enqueue(new Callback<APICallResults>() {
            @Override
            public void onResponse(Call<APICallResults> call, Response<APICallResults> response) {
                List<Movie> movies = response.body().getMovies();
                setRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<APICallResults> call, Throwable t) {

            }
        });
    }

    public void showTopRatedMovies() {
        Call<APICallResults> call = service.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_KEY);
        call.enqueue(new Callback<APICallResults>() {
            @Override
            public void onResponse(Call<APICallResults> call, Response<APICallResults> response) {
                List<Movie> movies = response.body().getMovies();
                setRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<APICallResults> call, Throwable t) {

            }
        });
    }

    public void setRecyclerView(List<Movie> movies) {
        moviesGrid = findViewById(R.id.movies_grid);
        moviesGrid.setHasFixedSize(true);
        adapter = new MoviesAdapter(movies);
        moviesGrid.setAdapter(adapter);
    }
}
