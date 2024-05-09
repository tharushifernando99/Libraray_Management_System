package com.example.libaray_management_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CopyAdapter extends RecyclerView.Adapter<CopyAdapter.CopyViewHolder> {

    private List<Copy> copyList;
    private OnCopyClickListener listener;

    public CopyAdapter(List<Copy> copyList, OnCopyClickListener listener) {
        this.copyList = copyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CopyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.copy_items, parent, false);
        return new CopyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CopyViewHolder holder, int position) {
        Copy copy = copyList.get(position);
        holder.bind(copy);
    }

    @Override
    public int getItemCount() {
        return copyList.size();
    }

    public interface OnCopyClickListener {
        void onCopyClick(int position);

        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }

    static class CopyViewHolder extends RecyclerView.ViewHolder {
        TextView AccessNoTextView;
        ImageView updateImageView, deleteImageView;
        OnCopyClickListener listener;

        public CopyViewHolder(@NonNull View itemView, OnCopyClickListener listener) {
            super(itemView);
            AccessNoTextView = itemView.findViewById(R.id.AccessNoTextView);
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
                            listener.onCopyClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Copy copy) {
            AccessNoTextView.setText(copy.getaccessNo());
        }
    }
}
