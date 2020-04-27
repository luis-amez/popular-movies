package com.luisamez.popularmovies.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luisamez.popularmovies.R;
import com.luisamez.popularmovies.model.Movie;
import com.luisamez.popularmovies.utils.PicassoLoader;

import java.util.List;

/**
 * RecyclerView adapter for {@link Movie}.
 */
public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final MovieClickListener onClickListener;

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies, MovieClickListener onClickListener) {
        this.movies = movies;
        this.onClickListener = onClickListener;
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

    public interface MovieClickListener {
        void onMovieClick(int movieIndex);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView posterView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.card_image);
            itemView.setOnClickListener(this);
        }

        void bind(int movieIndex) {
            PicassoLoader.loadImage(movies.get(movieIndex).getPosterPath(), posterView);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onMovieClick(getAdapterPosition());
        }
    }
}
