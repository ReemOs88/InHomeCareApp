package com.example.inhomecareapp.caregiver;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.chat.ChatActivity;
import com.example.inhomecareapp.customer.CustomerPost;
import com.example.inhomecareapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Map<String, Object> map = new HashMap<>();
                map.put("accept", true);

                FirebaseFirestore.getInstance().collection("posts")
                        .document(customerPost.getPostId()).update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(view.getContext(), "Post accepted", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        holder.contactCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChatActivity.class);
                intent.putExtra("userId", customerPost.getUserId());
                intent.putExtra("username", customerPost.getUsername());
                intent.putExtra("type", "caregiver");

                view.getContext().startActivity(intent);
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
