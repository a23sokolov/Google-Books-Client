package com.example.gbooks.googlebooksclient.manager;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.gbooks.googlebooksclient.App;
import com.example.gbooks.googlebooksclient.database.table.BookTable;
import com.example.gbooks.googlebooksclient.model.dto.Book;
import com.example.gbooks.googlebooksclient.other.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

import static com.example.gbooks.googlebooksclient.database.BookContentProvider.*;

/**
 * Created by a23sokolov on 23/09/2017.
 */

public class BookFavouriteManager {

    public BookFavouriteManager() {
        contentResolver = App.getInstance().getContentResolver();
        favBooks = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadFavouriteBooks();
            }
        }).start();
    }

    private static final Object lock = new Object();

    public static BookFavouriteManager instance;

    private final List<Book> favBooks;
    private final ContentResolver contentResolver;

    public static BookFavouriteManager getInstance() {
        synchronized (lock) {
            if (instance ==null) instance = new BookFavouriteManager();
            return instance;
        }
    }

    private void loadFavouriteBooks(){
        synchronized (lock) {
            Cursor cursor = null;
            try {
                final String[] projection = {BookTable.COLUMN_BOOK_ID, BookTable.COLUMN_TITLE,
                        BookTable.COLUMN_AUTHOR, BookTable.COLUMN_COVER_URL, BookTable.COLUMN_PREV_URL};
                cursor = contentResolver.query(BOOK_URI, projection, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        favBooks.add(new Book(cursor));
                    }
                }
            } catch (final Exception ignored) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    public List<Book> signFavBookInSearch(List<Book> books) {
        // TODO: should be optimized
        for(final Book curBook: books) {
            for(int i=0; i < favBooks.size(); i++) {
                if (curBook.getBookId().equals(favBooks.get(i).getBookId()))
                    curBook.setFav(true);
            }
        }
        return books;
    }

    public List<Book> getFavBooks() {
        synchronized (lock) {
            return favBooks;
        }
    }

    public void deleteFavBook(@NonNull final Book book) {
        synchronized (lock) {
            Book bookToRemove = null;
            for (final Book favBook : favBooks) {
                if (favBook.getBookId().equals(book.getBookId())) {
                    bookToRemove = favBook;
                }
            }

            if (bookToRemove != null) {
                favBooks.remove(bookToRemove);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String selection = BookTable.COLUMN_BOOK_ID + " = ?";
                        contentResolver.delete(BOOK_URI, selection, new String[]{book.getBookId()});
                    }
                }).start();
            }
        }
    }

    public void addFavBook(@NonNull final Book book) {
        synchronized (lock) {
            favBooks.add(book);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    contentResolver.insert(BOOK_URI, book.getContentValues());
                }
            }).start();
        }
    }
}
