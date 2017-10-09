package com.example.gbooks.googlebooksclient.other.di.search;

import com.example.gbooks.googlebooksclient.view.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by a23sokolov on 29/09/2017.
 */
@Singleton
@Component(modules = {SearchViewModule.class})
public interface SearchComponent {
    void inject(SearchActivity searchActivity);
}
