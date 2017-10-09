package com.example.gbooks.googlebooksclient.other.di;

import com.example.gbooks.googlebooksclient.model.ModelImpl;
import com.example.gbooks.googlebooksclient.presenter.BasePresenter;
import com.example.gbooks.googlebooksclient.presenter.PresenterSearch;
import com.example.gbooks.googlebooksclient.view.activity.FavouriteActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by a23sokolov on 25/09/2017.
 */

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(PresenterSearch presenterSearch);

    void inject(FavouriteActivity favouriteActivity);
}
