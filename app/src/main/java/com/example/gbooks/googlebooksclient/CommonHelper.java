package com.example.gbooks.googlebooksclient;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class CommonHelper {
    public static final String GOOGLE_BOOK_PREV_URL_PATTERN_2 = AndroidApplication.getInstance().getString(R.string.pattern_prev_url);

    public static String getFavouriteIcon(boolean isFavourite) {
        final Context ctx = AndroidApplication.getInstance().getApplicationContext();
        return isFavourite ? ctx.getString(R.string.favourite_on) : ctx.getString(R.string.favourite_off);
    }
}
