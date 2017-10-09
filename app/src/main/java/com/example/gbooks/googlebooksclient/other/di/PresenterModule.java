package com.example.gbooks.googlebooksclient.other.di;

import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.model.Model;
import com.example.gbooks.googlebooksclient.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by a23sokolov on 25/09/2017.
 */
@Module
public class PresenterModule {
    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    @Singleton
    BookFavouriteManager provideDatabasRepositoty() {
        return BookFavouriteManager.getInstance();
    }

    @Provides
    CompositeDisposable provideCompositeSubscription() {
        return new CompositeDisposable();
    }
}
