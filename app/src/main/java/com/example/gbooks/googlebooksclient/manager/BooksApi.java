package com.example.gbooks.googlebooksclient.manager;

import com.example.gbooks.googlebooksclient.model.BooksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a23sokolov on 19/09/2017.
 */

public interface BooksApi {
    public static final String BOOKS_ENDPOINT = "https://www.googleapis.com/books/";

    @GET("v1/volumes")
    Call<BooksResponse> getBookResponse(@Query("q") String query);
}
