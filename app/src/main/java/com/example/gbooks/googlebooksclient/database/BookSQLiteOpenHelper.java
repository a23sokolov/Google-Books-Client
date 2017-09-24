package com.example.gbooks.googlebooksclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gbooks.googlebooksclient.database.table.BookTable;

/**
 * Created by a23sokolov on 23/09/2017.
 */

public class BookSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gbooks";
    private static final int DATABASE_VERSION = 1;

    public BookSQLiteOpenHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        BookTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //ignore only one version;
    }
}
