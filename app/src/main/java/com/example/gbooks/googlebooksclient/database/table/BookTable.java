package com.example.gbooks.googlebooksclient.database.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a23sokolov on 23/09/2017.
 */

public class BookTable {

    public static final String TABLE_NAME = "book_table";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOK_ID = "book_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "authors";
    public static final String COLUMN_COVER_URL = "cover_url";
    public static final String COLUMN_PREV_URL = "prev_url";

    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + '('
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_BOOK_ID + " text not null, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_AUTHOR + " text not null, "
            + COLUMN_PREV_URL + " text not null, "
            + COLUMN_COVER_URL + " text" +

            ");";

    public static void onCreate(final SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
