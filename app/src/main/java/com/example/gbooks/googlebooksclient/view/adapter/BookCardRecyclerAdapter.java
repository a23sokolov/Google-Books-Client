package com.example.gbooks.googlebooksclient.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gbooks.googlebooksclient.R;
import com.example.gbooks.googlebooksclient.model.Book;
import com.example.gbooks.googlebooksclient.view.component.BookItemView;

import java.util.ArrayList;
import java.util.List;

import static com.example.gbooks.googlebooksclient.view.adapter.BookCardRecyclerAdapter.ItemType.BOOK_ITEM;
import static com.example.gbooks.googlebooksclient.view.adapter.BookCardRecyclerAdapter.ItemType.EMPTY_LIST;

/**
 * Created by a23sokolov on 22/09/2017.
 */

public class BookCardRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Book> books;

    enum ItemType {
        EMPTY_LIST,
        BOOK_ITEM
    }

    public BookCardRecyclerAdapter(Context context) {
        books = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                final View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_empty, parent, false);
                return new EmptySearchHolder(emptyView);
            default:
                final View bookItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
                return new BookViewHolder(bookItem);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && books.size() == 0)
            return EMPTY_LIST.ordinal();
        else return BOOK_ITEM.ordinal();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != BOOK_ITEM.ordinal()) return;
        final BookViewHolder bookViewHolder = (BookViewHolder) holder;
        final Book book = books.get(position);
        bookViewHolder.bookItemView.setBook(book);
    }

    public void updateBooksList(List<Book> bookList) {
        books.clear();
        books.addAll(bookList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        BookItemView bookItemView;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookItemView = (BookItemView) itemView.findViewById(R.id.book_item);
        }
    }

    class EmptySearchHolder extends RecyclerView.ViewHolder {
        public EmptySearchHolder(View itemView) {
            super(itemView);
        }
    }
}
