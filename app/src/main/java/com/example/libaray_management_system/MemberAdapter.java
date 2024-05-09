package com.example.libaray_management_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    private List<Member> memberList;
    private OnMemberClickListener listener;

    public MemberAdapter(List<Member> memberList, OnMemberClickListener listener) {
        this.memberList = memberList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_items, parent, false);
        return new MemberViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.bind(member);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public interface OnMemberClickListener {
        void onMemberClick(int position);

        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView NameTextView, AddressTextView, PhoneTextView,UnpaidTextView;
        ImageView updateImageView, deleteImageView;
        OnMemberClickListener listener;

        public MemberViewHolder(@NonNull View itemView, OnMemberClickListener listener) {
            super(itemView);
            NameTextView = itemView.findViewById(R.id.NameTextView);
            AddressTextView = itemView.findViewById(R.id.AddressTextView);
            PhoneTextView = itemView.findViewById(R.id.PhoneTextView);
            UnpaidTextView = itemView.findViewById(R.id.UnpaidTextView);
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

            // Set click listener for the entire item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMemberClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Member member) {
            NameTextView.setText(member.getName());
            AddressTextView.setText(member.getAddress());
            PhoneTextView.setText(member.getPhone());
            UnpaidTextView.setText(member.getUnpaid());
        }
    }
}
