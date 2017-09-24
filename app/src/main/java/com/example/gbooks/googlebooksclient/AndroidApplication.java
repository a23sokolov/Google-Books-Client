package com.example.gbooks.googlebooksclient;

import android.app.Application;

import com.example.gbooks.googlebooksclient.manager.BookFavouriteManager;
import com.example.gbooks.googlebooksclient.manager.NetworkManager;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class AndroidApplication extends Application{

    private static AndroidApplication instance;

    private NetworkManager networkManager;
    private BookFavouriteManager favouriteManager;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        networkManager = new NetworkManager();
        favouriteManager = BookFavouriteManager.getInstance();
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public BookFavouriteManager getFavouriteManager() {
        return favouriteManager;
    }

    public static AndroidApplication getInstance() {
        return instance;
    }
}
