package com.example.gbooks.googlebooksclient.presenter;

import com.example.gbooks.googlebooksclient.App;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by a23sokolov on 02/10/2017.
 */

public abstract class BasePresenter {
    @Inject
    protected CompositeDisposable compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    public void onDetach() {
        compositeSubscription.clear();
    }

    protected void addSubscription(Disposable disposable) {
        compositeSubscription.add(disposable);
    }
}
