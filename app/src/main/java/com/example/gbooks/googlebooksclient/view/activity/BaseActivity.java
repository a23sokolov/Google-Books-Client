package com.example.gbooks.googlebooksclient.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.model.dto.Book;
import com.example.gbooks.googlebooksclient.view.adapter.BookCardRecyclerAdapter;
import com.example.gbooks.googlebooksclient.view.component.BookItemView;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BookItemView.FavouriteListener{
    protected BookCardRecyclerAdapter mAdapter;
    @Override
    public void setFavourite(Book book) {
        if (book.isFav()) BookFavouriteManager.getInstance().addFavBook(book);
        else BookFavouriteManager.getInstance().deleteFavBook(book);
        final boolean remove = (this instanceof FavouriteActivity) ? true : false;
        mAdapter.changeBookFavStat(book, remove);
    }

    protected void showMessage(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
