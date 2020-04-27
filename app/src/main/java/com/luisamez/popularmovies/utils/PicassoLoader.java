package com.luisamez.popularmovies.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public final class PicassoLoader {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w500";

    private PicassoLoader() {
    }

    public static void loadImage(String path, ImageView view) {
        String posterFullPath = new StringBuilder()
                .append(BASE_URL)
                .append(IMAGE_SIZE)
                .append(path)
                .toString();
        Picasso.get().load(posterFullPath).into(view);
    }
}
