package com.example.gbooks.googlebooksclient.presenter;

import com.example.gbooks.googlebooksclient.App;
import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.model.Model;
import com.example.gbooks.googlebooksclient.model.dto.Book;
import com.example.gbooks.googlebooksclient.model.dto.BooksResponse;
import com.example.gbooks.googlebooksclient.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by a23sokolov on 25/09/2017.
 */

public class PresenterSearch extends BasePresenter {
    @Inject
    protected Model model;
    @Inject
    protected BookFavouriteManager bookFavouriteManager;

    private SearchView view;

    private List<Book> lastBooksList;

    @Inject
    public PresenterSearch() {
    }

    public PresenterSearch(SearchView view) {
        this.view = view;
        App.getComponent().inject(this);
    }

    public void queryChanged(String query) {
        view.showContentLoading();
        Disposable subscription = model
                .getBooksList(query)
                .map(BooksResponse::getBookList)
                .subscribe(list -> {
                    if (list != null && !list.isEmpty()) {
                        lastBooksList = bookFavouriteManager.signFavBookInSearch(list);
                        view.updateResult(lastBooksList);
                    } else view.showEmptyResultsView();
                    view.hideContentLoading();
                }, error -> {
                    lastBooksList = null;
                    view.showContentError();
                });
        addSubscription(subscription);
    }


    public void booksFavChanged(Book book) {
        //TODO: update favBookManager;
    }

    private void previousResponse() {
        if (lastBooksList != null && lastBooksList.size() > 0) {
            view.updateResult(bookFavouriteManager.signFavBookInSearch(lastBooksList));
        }
    }

    public void setState(List<Book> books) {
        lastBooksList = books;
        previousResponse();
    }

    public ArrayList<Book> getState() {
        return (ArrayList<Book>) lastBooksList;
    }
}
