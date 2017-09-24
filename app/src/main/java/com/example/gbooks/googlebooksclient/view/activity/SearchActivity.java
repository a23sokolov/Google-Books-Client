package com.example.gbooks.googlebooksclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gbooks.googlebooksclient.AndroidApplication;
import com.example.gbooks.googlebooksclient.R;
import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.manager.NetworkManager;
import com.example.gbooks.googlebooksclient.model.Book;
import com.example.gbooks.googlebooksclient.view.adapter.BookCardRecyclerAdapter;

import java.util.List;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class SearchActivity extends BaseActivity implements NetworkManager.Listener{
    private BookFavouriteManager bookFavouriteManager;
    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new BookCardRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        bookFavouriteManager = BookFavouriteManager.getInstance();
        networkManager = ((AndroidApplication) this.getApplicationContext()).getNetworkManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        networkManager.setListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        networkManager.removeListener();
    }

    @Override
    public void updateAdapter() {
        mAdapter.updateBooksList(networkManager.getLastResponse());
    }

    @Override
    public void requestSuccess(@NonNull List<Book> books) {
        if (books.size() == 0) showMessage(getString(R.string.response_nothing_found));
        mAdapter.updateBooksList(bookFavouriteManager.signFavBookInSearch(books));
    }

    @Override
    public void requestFailed() {
        showMessage(getString(R.string.request_failed));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                networkManager.doRequest(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //ignore
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favourite:
                Intent intent = new Intent(this, FavouriteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
