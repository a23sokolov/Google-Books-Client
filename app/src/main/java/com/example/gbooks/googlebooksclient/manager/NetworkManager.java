package com.example.gbooks.googlebooksclient.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gbooks.googlebooksclient.model.Book;
import com.example.gbooks.googlebooksclient.model.BooksResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a23sokolov on 23/09/2017.
 */

public class NetworkManager {

    private BooksApi booksApi;
    @Nullable
    private List<Book> lastResponse = new ArrayList<>();

    public interface Listener {
        public void requestSuccess(@NonNull List<Book> books);
        public void requestFailed();
    }

    Listener listener;

    public NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BooksApi.BOOKS_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        booksApi = retrofit.create(BooksApi.class);
    }

    public void doRequest(String query) {
        booksApi.getBookResponse(query).enqueue(new CallbackReport());
    }

    private void requestSuccess(@Nullable List<Book> books) {
        if (books == null) books = new ArrayList<>();
        lastResponse = books;
        if (listener != null) listener.requestSuccess(books);
    }

    private void requestFailed() {
        if (listener != null) listener.requestFailed();
    }

    private class CallbackReport implements Callback<BooksResponse> {

        @Override
        public void onResponse(@NonNull Call<BooksResponse> call, @NonNull Response<BooksResponse> response) {
            requestSuccess(response.body().getBookList());
        }

        @Override
        public void onFailure(@NonNull Call<BooksResponse> call, @NonNull Throwable t) {
            requestFailed();
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void removeListener() {
        this.listener = null;
    }

    @Nullable
    public List<Book> getLastResponse() {
        return lastResponse;
    }
}
