package com.example.gbooks.googlebooksclient.other.di.search;

import com.example.gbooks.googlebooksclient.presenter.PresenterSearch;
import com.example.gbooks.googlebooksclient.view.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by a23sokolov on 29/09/2017.
 */

@Module
public class SearchViewModule {

    private SearchView view;

    public SearchViewModule(SearchView view) {
        this.view = view;
    }

    @Provides
    PresenterSearch provideRepoListPresenter() {
        return new PresenterSearch(view);
    }
}
