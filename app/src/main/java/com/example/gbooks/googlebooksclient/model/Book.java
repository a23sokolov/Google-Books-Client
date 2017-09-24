package com.example.gbooks.googlebooksclient.model;

/**
 * Created by a23sokolov on 23/09/2017.
 */

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.gbooks.googlebooksclient.database.table.BookTable;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Book {

    private boolean isFav;
    @SerializedName("id")
    private String bookId;

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;


    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public String getTitle() {
        return volumeInfo.title;
    }

    public void setTitle(String title) {
        this.volumeInfo.title = title;
    }

    public String getAuthor() {
        return volumeInfo.authors != null && volumeInfo.authors.size() > 0 ? volumeInfo.authors.get(0): "Author not available";
    }
    public List<String> getAuthors() {
        return volumeInfo.authors;
    }

    public void setAuthors(List<String> authors) {
        this.volumeInfo.authors = authors;
    }

    @Nullable
    public String getImageLink() {
        return volumeInfo.imageLink != null ? volumeInfo.imageLink.cover: null;
    }

    public void setImageLink(ImageLinks imageLink) {
        this.volumeInfo.imageLink = imageLink;
    }

    public String getPreviewLink() {
        return volumeInfo.previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.volumeInfo.previewLink = previewLink;
    }

    private class VolumeInfo {
        @SerializedName("title")
        private String title;

        @SerializedName("authors")
        private List<String> authors;

        @SerializedName("imageLinks")
        private ImageLinks imageLink;

        @SerializedName("previewLink")
        private String previewLink;

        @Override
        public String toString() {
            return "VolumeInfo{" +
                    "title='" + title + '\'' +
                    ", authors=" + authors +
                    ", imageLink=" + (imageLink != null ? imageLink.cover: null) +
                    ", previewLink='" + previewLink + '\'' +
                    '}';
        }
    }

    private class ImageLinks {
        @SerializedName("smallThumbnail")
        private String cover;
    }

    public Book(Cursor cursor) {
        volumeInfo = new VolumeInfo();
        volumeInfo.imageLink = new ImageLinks();

        bookId = cursor.getString(cursor.getColumnIndex(BookTable.COLUMN_BOOK_ID));
        volumeInfo.authors = Arrays.asList(cursor.getString(cursor.getColumnIndex(BookTable.COLUMN_AUTHOR)));
        volumeInfo.imageLink.cover = cursor.getString(cursor.getColumnIndex(BookTable.COLUMN_COVER_URL));
        volumeInfo.title = cursor.getString(cursor.getColumnIndex(BookTable.COLUMN_TITLE));
        volumeInfo.previewLink = cursor.getString(cursor.getColumnIndex(BookTable.COLUMN_PREV_URL));
    }

    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(BookTable.COLUMN_BOOK_ID, bookId);
        values.put(BookTable.COLUMN_AUTHOR, getAuthor());
        values.put(BookTable.COLUMN_COVER_URL, getImageLink());
        values.put(BookTable.COLUMN_TITLE, getTitle());
        values.put(BookTable.COLUMN_PREV_URL, getPreviewLink());
        return values;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isFav=" + isFav +
                ", bookId='" + bookId + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
