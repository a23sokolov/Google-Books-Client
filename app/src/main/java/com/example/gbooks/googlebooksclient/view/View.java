package com.example.gbooks.googlebooksclient.view;

/**
 * Created by a23sokolov on 08/10/2017.
 */

public interface View {
    void showContentLoading();
    void hideContentLoading();

    void showContentError();
    void hideContentError();

    void showEmptyResultsView();
    void hideEmptyResultsView();
}
