package com.example.gbooks.googlebooksclient.view;

import com.example.gbooks.googlebooksclient.model.dto.Book;

import java.util.List;

/**
 * Created by a23sokolov on 25/09/2017.
 */

public interface SearchView extends View{
    void updateResult(List<Book> books);
}
