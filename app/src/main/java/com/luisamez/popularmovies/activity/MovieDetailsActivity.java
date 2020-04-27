package com.luisamez.popularmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisamez.popularmovies.R;
import com.luisamez.popularmovies.model.Movie;
import com.luisamez.popularmovies.utils.PicassoLoader;

import static com.luisamez.popularmovies.activity.MainActivity.INTENT_EXTRA_MOVIE;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleView;
    private ImageView posterView;
    private TextView releaseDateView;
    private TextView voteAverageView;
    private TextView synopsisView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        titleView = findViewById(R.id.movie_title);
        posterView = findViewById(R.id.movie_poster);
        releaseDateView = findViewById(R.id.movie_release_date);
        voteAverageView = findViewById(R.id.movie_vote_average);
        synopsisView = findViewById(R.id.movie_synopsis);
        populateLayout();
    }

    private void populateLayout() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(INTENT_EXTRA_MOVIE)) {
                Movie movie = intent.getParcelableExtra(INTENT_EXTRA_MOVIE);
                titleView.setText(movie.getTitle());
                PicassoLoader.loadImage(movie.getPosterPath(), posterView);
                releaseDateView.setText(movie.getReleaseDate());
                voteAverageView.setText(movie.getVoteAverage().toString());
                synopsisView.setText(movie.getSynopsis());
            }
        }
    }
}
