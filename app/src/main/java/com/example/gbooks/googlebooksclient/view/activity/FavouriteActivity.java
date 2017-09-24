package com.example.gbooks.googlebooksclient.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gbooks.googlebooksclient.R;
import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.view.adapter.BookCardRecyclerAdapter;
import com.example.gbooks.googlebooksclient.view.component.BookItemView;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class FavouriteActivity extends BaseActivity implements BookItemView.FavouriteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new BookCardRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.updateBooksList(BookFavouriteManager.getInstance().getFavBooks());
    }

    @Override
    public void updateAdapter() {
        mAdapter.updateBooksList(BookFavouriteManager.getInstance().getFavBooks());
    }


}
