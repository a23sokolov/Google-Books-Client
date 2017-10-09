package com.example.gbooks.googlebooksclient.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gbooks.googlebooksclient.R;
import com.example.gbooks.googlebooksclient.model.dto.Book;
import com.example.gbooks.googlebooksclient.other.di.search.DaggerSearchComponent;
import com.example.gbooks.googlebooksclient.other.di.search.SearchComponent;
import com.example.gbooks.googlebooksclient.other.di.search.SearchViewModule;
import com.example.gbooks.googlebooksclient.presenter.PresenterSearch;
import com.example.gbooks.googlebooksclient.view.SearchView;
import com.example.gbooks.googlebooksclient.view.adapter.BookCardRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class SearchActivity extends BaseActivity implements SearchView {
    private static final String LAST_RESPONSE = "com.example.gbooks.googlebooksclient.books_response";
    private static final String LAST_QUERY = "com.example.gbooks.googlebooksclient.last_query";

    @Inject
    protected PresenterSearch presenter;

    private SearchComponent searchComponent;

    private ProgressDialog progressDialog;
    private android.support.v7.widget.SearchView searchView;
    private MenuItem searchItem;

    private String lastQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // for testing.
        if( searchComponent == null) {
            searchComponent = DaggerSearchComponent.builder()
                    .searchViewModule(new SearchViewModule(this))
                    .build();
        }
        searchComponent.inject(this);

        progressDialog = new ProgressDialog(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new BookCardRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            lastQuery = savedInstanceState.getString(LAST_QUERY);
            presenter.setState(savedInstanceState.getParcelableArrayList(LAST_RESPONSE));
        }
    }

    //for testing
    public void setSearchComponent(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        searchItem = menu.findItem(R.id.action_search);
        searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);

        if (!TextUtils.isEmpty(lastQuery)) {
            searchItem.expandActionView();
            searchView.setIconified(false);
            searchView.setQuery(lastQuery, true);
            searchView.clearFocus();
        }

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.queryChanged(query);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(LAST_RESPONSE, presenter.getState());
        outState.putString(LAST_QUERY, String.valueOf(searchView.getQuery()));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateResult(List<Book> books) {
        mAdapter.updateBooksList(books);
    }

    @Override
    public void showContentLoading() {
        progressDialog.show();
    }

    @Override
    public void hideContentLoading() {
        progressDialog.hide();
    }

    @Override
    public void showContentError() {
        showMessage(getString(R.string.request_failed));
    }

    @Override
    public void hideContentError() {
        //ignored
    }

    @Override
    public void showEmptyResultsView() {
        showMessage(getString(R.string.response_nothing_found));
    }

    @Override
    public void hideEmptyResultsView() {
        //ignored
    }
}
