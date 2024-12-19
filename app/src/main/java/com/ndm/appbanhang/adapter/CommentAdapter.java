package com.ndm.appbanhang.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ndm.appbanhang.R;
import com.ndm.appbanhang.enitities.Comment;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.tvFullName.setText(comment.getFull_name());
        holder.tvContent.setText(comment.getContent());
        holder.tvCreatedAt.setText(comment.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFullName, tvContent, tvCreatedAt;

        public CommentViewHolder(View view) {
            super(view);
            tvFullName = view.findViewById(R.id.tv_full_name);
            tvContent = view.findViewById(R.id.tv_content);
            tvCreatedAt = view.findViewById(R.id.tv_created_at);
        }
    }
}
