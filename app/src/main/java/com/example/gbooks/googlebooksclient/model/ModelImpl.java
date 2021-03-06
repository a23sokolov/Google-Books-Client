package com.example.gbooks.googlebooksclient.model;

import android.util.Log;

import com.example.gbooks.googlebooksclient.App;
import com.example.gbooks.googlebooksclient.model.api.ApiInterface;
import com.example.gbooks.googlebooksclient.model.dto.BooksResponse;
import com.example.gbooks.googlebooksclient.other.Const;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by a23sokolov on 25/09/2017.
 */

public class ModelImpl implements Model{


    @Inject
    ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<BooksResponse> getBooksList(String query) {
        Log.i("$$$$", "ModelImpl.getBooksList query = " + query + ", apiInterface " +apiInterface);
        return apiInterface
                .getBooks(query)
                .subscribeOn(ioThread)
                .observeOn(uiThread);
    }

}
