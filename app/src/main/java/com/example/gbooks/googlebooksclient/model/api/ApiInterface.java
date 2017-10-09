package com.example.gbooks.googlebooksclient.model.api;

import com.example.gbooks.googlebooksclient.model.dto.BooksResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a23sokolov on 19/09/2017.
 */

public interface ApiInterface {

    @GET("v1/volumes")
    Observable<BooksResponse> getBooks(@Query("q") String query);
}
