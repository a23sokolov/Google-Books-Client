package com.example.gbooks.googlebooksclient;

import android.app.Application;

import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.other.di.AppComponent;
import com.example.gbooks.googlebooksclient.other.di.DaggerAppComponent;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class App extends Application{

    private static App instance;

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        component = buildComponent();
    }

    public static App getInstance() {
        return instance;
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
