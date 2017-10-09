package com.example.gbooks.googlebooksclient;

import android.content.Context;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class CommonHelper {
    public static final String GOOGLE_BOOK_PREV_URL_PATTERN = App.getInstance().getString(R.string.pattern_prev_url);

    public static String getFavouriteIcon(boolean isFavourite) {
        final Context ctx = App.getInstance().getApplicationContext();
        return isFavourite ? ctx.getString(R.string.favourite_on) : ctx.getString(R.string.favourite_off);
    }
}
