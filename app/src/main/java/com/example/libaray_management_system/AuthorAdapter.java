package com.example.libaray_management_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {

    private List<Author> authorList;
    private OnAuthorClickListener listener;

    public AuthorAdapter(List<Author> authorList, OnAuthorClickListener listener) {
        this.authorList = authorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_items, parent, false);
        return new AuthorViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author = authorList.get(position);
        holder.bind(author);
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    public interface OnAuthorClickListener {
        void onAuthorClick(int position);

        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }

    static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView NameTextView;
        ImageView updateImageView, deleteImageView;
        OnAuthorClickListener listener;

        public AuthorViewHolder(@NonNull View itemView, OnAuthorClickListener listener) {
            super(itemView);
            NameTextView = itemView.findViewById(R.id.NameTextView);
            updateImageView = itemView.findViewById(R.id.updateImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            this.listener = listener;


            updateImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onUpdateClick(position);
                        }
                    }
                }
            });

            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAuthorClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Author author) {
            NameTextView.setText(author.getName());
        }
    }
}
