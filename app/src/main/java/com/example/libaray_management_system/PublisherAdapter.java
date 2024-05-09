package com.example.libaray_management_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.PublisherViewHolder> {

    private List<Publisher> publisherList;
    private OnPublisherClickListener listener;

    public PublisherAdapter(List<Publisher> publisherList, OnPublisherClickListener listener) {
        this.publisherList = publisherList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publisher_item, parent, false);
        return new PublisherViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PublisherViewHolder holder, int position) {
        Publisher publisher = publisherList.get(position);
        holder.bind(publisher);
    }

    @Override
    public int getItemCount() {
        return publisherList.size();
    }

    public interface OnPublisherClickListener {
        void onPublisherClick(int position);

        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }

    static class PublisherViewHolder extends RecyclerView.ViewHolder {
        TextView NameTextView, AddressTextView, PhoneTextView;
        ImageView updateImageView, deleteImageView;
        OnPublisherClickListener listener;

        public PublisherViewHolder(@NonNull View itemView, OnPublisherClickListener listener) {
            super(itemView);
            NameTextView = itemView.findViewById(R.id.NameTextView);
            AddressTextView = itemView.findViewById(R.id.AddressTextView);
            PhoneTextView = itemView.findViewById(R.id.PhoneTextView);
            updateImageView = itemView.findViewById(R.id.updateImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            this.listener = listener;

            // Set click listeners for update and delete ImageViews
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

            // Set click listener for the entire item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPublisherClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Publisher publisher) {
            NameTextView.setText(publisher.getName());
            AddressTextView.setText(publisher.getAddress());
            PhoneTextView.setText(publisher.getPhone());
        }
    }
}
