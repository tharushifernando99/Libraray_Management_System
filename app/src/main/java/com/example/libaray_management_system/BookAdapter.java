package com.example.libaray_management_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;
    private OnBookClickListener listener;

    public BookAdapter(List<Book> bookList, OnBookClickListener listener) {
        this.bookList = bookList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items, parent, false);
        return new BookViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface OnBookClickListener {
        void onBookClick(int position);
        void onUpdateClick(int position);
        void onDeleteClick(int position);
    }

    static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView, publisherTextView;
        ImageView updateImageView, deleteImageView;
        OnBookClickListener listener;

        public BookViewHolder(@NonNull View itemView, OnBookClickListener listener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            publisherTextView = itemView.findViewById(R.id.publisherTextView);
            updateImageView = itemView.findViewById(R.id.updateImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            updateImageView.setOnClickListener(this);
            deleteImageView.setOnClickListener(this);
        }

        public void bind(Book book) {
            titleTextView.setText(book.getTitle());
            publisherTextView.setText(book.getPublisherName());
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == updateImageView.getId()) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onUpdateClick(position);
                    }
                }
            } else if (v.getId() == deleteImageView.getId()) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            } else {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onBookClick(position);
                    }
                }
            }
        }
    }
}
