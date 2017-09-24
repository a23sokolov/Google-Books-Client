package com.example.gbooks.googlebooksclient.view.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.gbooks.googlebooksclient.CommonHelper;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class FavouriteView extends android.support.v7.widget.AppCompatTextView{
    private static final String FONT_PATH = "fonts/MaterialIcons-Regular.ttf";
    private final Typeface materialFont;
    public FavouriteView(Context context) {
        super(context);
        materialFont = Typeface.createFromAsset(context.getAssets(), FONT_PATH);
        setTypeface(materialFont);
    }

    public FavouriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        materialFont = Typeface.createFromAsset(context.getAssets(), FONT_PATH);
        setTypeface(materialFont);
    }

    public FavouriteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        materialFont = Typeface.createFromAsset(context.getAssets(), FONT_PATH);
        setTypeface(materialFont);
    }

    public void setImage(boolean isFavourite) {
        setText(CommonHelper.getFavouriteIcon(isFavourite));
    }
}
