package com.example.libaray_management_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    private List<Branch> branchList;
    private OnBranchClickListener listener;

    public BranchAdapter(List<Branch> branchList, OnBranchClickListener listener) {
        this.branchList = branchList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_items, parent, false);
        return new BranchViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        Branch branch = branchList.get(position);
        holder.bind(branch);
    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public interface OnBranchClickListener {
        void onBranchClick(int position);

        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }

    static class BranchViewHolder extends RecyclerView.ViewHolder {
        TextView NameTextView, AddressTextView;
        ImageView updateImageView, deleteImageView;
        OnBranchClickListener listener;

        public BranchViewHolder(@NonNull View itemView, OnBranchClickListener listener) {
            super(itemView);
            NameTextView = itemView.findViewById(R.id.NameTextView);
            AddressTextView = itemView.findViewById(R.id.AddressTextView);
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
                            listener.onBranchClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Branch branch) {
            NameTextView.setText(branch.getName());
            AddressTextView.setText(branch.getAddress());
        }
    }
}
