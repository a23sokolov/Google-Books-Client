package com.example.gbooks.googlebooksclient.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gbooks.googlebooksclient.database.table.BookTable;

import java.util.HashMap;

/**
 * Created by a23sokolov on 23/09/2017.
 */

public class BookContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.gbooks.contentprovider";
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final HashMap<Integer, String> tableMap = new HashMap<>();

    private static final String BOOK_PATH = "caller_id_blocked";
    public static final Uri BOOK_URI = Uri.parse("content://" + AUTHORITY + '/' + BOOK_PATH);
    private static final int BOOK_CODE = 10;
    private static final int BOOK_ID_CODE = 20;


    static {
        uriMatcher.addURI(AUTHORITY, BOOK_PATH, BOOK_CODE);
        uriMatcher.addURI(AUTHORITY, BOOK_PATH + "/#", BOOK_ID_CODE);

        tableMap.put(BOOK_CODE, BookTable.TABLE_NAME);
    }

    private BookSQLiteOpenHelper dbHelper;
    @Override
    public boolean onCreate() {
        dbHelper = new BookSQLiteOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        final int uriType = uriMatcher.match(uri);
        if (!tableMap.containsKey(uriType))
            throw new IllegalArgumentException("Wrong URI: " + uri);
        final String table = tableMap.get(uriType);
        queryBuilder.setTables(table);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int uriType = uriMatcher.match(uri);
        if (!tableMap.containsKey(uriType))
            throw new IllegalArgumentException("Wrong URI: " + uri);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final String table = tableMap.get(uriType);
        final long rowID = db.insert(table, null, contentValues);
        Uri resultUri = null;
        if (rowID != -1) {
            resultUri = ContentUris.withAppendedId(uri, rowID);
            getContext().getContentResolver().notifyChange(resultUri, null);
        }
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int uriType = uriMatcher.match(uri);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (!tableMap.containsKey(uriType))
            throw new IllegalArgumentException("Wrong URI: " + uri);
        final String table = tableMap.get(uriType);
        final int cnt = db.delete(table, selection, selectionArgs);
        if (cnt > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        //ignored nothing to update.
        return 0;
    }
}
