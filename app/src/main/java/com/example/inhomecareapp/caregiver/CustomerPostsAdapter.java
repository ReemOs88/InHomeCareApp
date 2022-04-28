package com.example.inhomecareapp.caregiver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.CustomerPost;
import com.example.inhomecareapp.R;

import java.util.List;

public class CustomerPostsAdapter extends RecyclerView.Adapter<CustomerPostsAdapter.CustomerPostsViewHolder> {
    List<CustomerPost> posts;

    public CustomerPostsAdapter(List<CustomerPost> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public CustomerPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerPostsViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.customer_posts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPostsViewHolder holder, int position) {
        CustomerPost customerPost = posts.get(position);

        holder.postDescriptionTv.setText(customerPost.getPostContent());

        holder.acceptPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.contactCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {

        return posts.size();
    }

    class CustomerPostsViewHolder extends RecyclerView.ViewHolder {
        TextView postDescriptionTv;
        Button acceptPostBtn, contactCustomerBtn;

        public CustomerPostsViewHolder(@NonNull View itemView) {
            super(itemView);
            postDescriptionTv = itemView.findViewById(R.id.post_description);
            acceptPostBtn = itemView.findViewById(R.id.accept_post_btn);
            contactCustomerBtn = itemView.findViewById(R.id.contact_customer_btn);
        }
    }


}
