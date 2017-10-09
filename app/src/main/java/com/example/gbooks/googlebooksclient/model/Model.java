package com.example.gbooks.googlebooksclient.model;

import com.example.gbooks.googlebooksclient.model.dto.Book;
import com.example.gbooks.googlebooksclient.model.dto.BooksResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a23sokolov on 25/09/2017.
 */

public interface Model {
    @GET("v1/volumes")
    Observable<BooksResponse> getBooksList(@Query("q") String query);
}
