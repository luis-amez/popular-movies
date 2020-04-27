package com.luisamez.popularmovies.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.luisamez.popularmovies.BuildConfig;
import com.luisamez.popularmovies.R;
import com.luisamez.popularmovies.controller.MoviesAdapter;
import com.luisamez.popularmovies.model.APICallResults;
import com.luisamez.popularmovies.model.Movie;
import com.luisamez.popularmovies.network.MovieDBAPIService;
import com.luisamez.popularmovies.network.RetrofitInstance;
import com.luisamez.popularmovies.utils.AutofitRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MovieClickListener {

    public static final String INTENT_EXTRA_MOVIE = "Movie";

    private AutofitRecyclerView moviesGrid;
    private MoviesAdapter adapter;
    private MovieDBAPIService service;
    private List<Movie> movies;
    private ProgressBar progressBar;
    private TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_circular);
        errorView = findViewById(R.id.error_message);
        moviesGrid = findViewById(R.id.movies_grid);
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
        showProgressBar();
        if (item.getItemId() == R.id.action_most_popular) {
            showMostPopularMovies();
            return true;
        } else if (item.getItemId() == R.id.action_top_rated) {
            showTopRatedMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClick(int movieIndex) {
        if (movies != null) {
            Intent intent = new Intent(/* context */ this, MovieDetailsActivity.class);
            intent.putExtra(INTENT_EXTRA_MOVIE, movies.get(movieIndex));
            startActivity(intent);
        }
    }

    private void showMostPopularMovies() {
        Call<APICallResults> call = service.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);
        call.enqueue(new Callback<APICallResults>() {
            @Override
            public void onResponse(Call<APICallResults> call, Response<APICallResults> response) {
                movies = response.body().getMovies();
                setRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<APICallResults> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void showTopRatedMovies() {
        Call<APICallResults> call = service.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_KEY);
        call.enqueue(new Callback<APICallResults>() {
            @Override
            public void onResponse(Call<APICallResults> call, Response<APICallResults> response) {
                movies = response.body().getMovies();
                setRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<APICallResults> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void setRecyclerView(List<Movie> movies) {
        moviesGrid.setHasFixedSize(true);
        adapter = new MoviesAdapter(movies, /* onClickListener */ this);
        moviesGrid.setAdapter(adapter);
        showMoviesGrid();
    }

    private void showMoviesGrid() {
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        moviesGrid.setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {
        errorView.setVisibility(View.GONE);
        moviesGrid.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        progressBar.setVisibility(View.GONE);
        moviesGrid.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
