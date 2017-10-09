package com.example.gbooks.googlebooksclient.view.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gbooks.googlebooksclient.CommonHelper;
import com.example.gbooks.googlebooksclient.R;
import com.example.gbooks.googlebooksclient.model.dto.Book;
import com.squareup.picasso.Picasso;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class BookItemView extends LinearLayout implements View.OnClickListener{

    public interface FavouriteListener {
        public void setFavourite(Book book);
    }

    private Picasso picasso;
    private ImageView coverImg;
    private TextView title;
    private TextView author;
    private TextView prevLink;
    private FavouriteIconView favImg;

    private boolean status;
    private Book book;

    public BookItemView(Context context) {
        super(context);
    }

    public BookItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setWeightSum(6);

        picasso = Picasso.with(context);

        inflate(context, R.layout.book_item_view, this);
        coverImg = (ImageView) findViewById(R.id.book_cover);
        title = (TextView) findViewById(R.id.book_title);
        author = (TextView) findViewById(R.id.book_author);
        prevLink = (TextView) findViewById(R.id.book_link_prev);
        favImg = (FavouriteIconView) findViewById(R.id.fav_btn);
    }

    public void setBook(Book bookData) {
        book = bookData;
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        final String link = CommonHelper.GOOGLE_BOOK_PREV_URL_PATTERN.replace("%s", book.getPreviewLink());
        prevLink.setClickable(true);
        prevLink.setMovementMethod(LinkMovementMethod.getInstance());
        prevLink.setText(Html.fromHtml(link));
        picasso.load(book.getImageLink())
                .into(coverImg);

        favImg.setImage(bookData.isFav());
        favImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        book.setFav(!book.isFav());
        ((FavouriteListener) getContext()).setFavourite(book);
    }
}
