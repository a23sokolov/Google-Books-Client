package com.example.gbooks.googlebooksclient.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by a23sokolov on 23/09/2017.
 */

public class BooksResponse {
    @SerializedName("items")
    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
