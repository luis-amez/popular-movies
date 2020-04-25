package com.luisamez.popularmovies.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisamez.popularmovies.R;
import com.luisamez.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * RecyclerView adapter for {@link Movie}.
 * */
public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.movie_grid_item,
                viewGroup,
                /* attachToRoot */false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder movieViewHolder, int movieIndex) {
        movieViewHolder.bind(movieIndex);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final String BASE_URL = "https://image.tmdb.org/t/p/";
        private final String IMAGE_SIZE = "w500";

        private ImageView posterView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.card_image);
        }

        void bind(int movieIndex) {
            String posterPath = new StringBuilder()
                    .append(BASE_URL)
                    .append(IMAGE_SIZE)
                    .append(movies.get(movieIndex).getPosterPath())
                    .toString();
            Picasso.get().load(posterPath).into(posterView);
        }
    }
}
