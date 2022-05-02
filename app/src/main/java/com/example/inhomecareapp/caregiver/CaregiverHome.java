package com.example.inhomecareapp.caregiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inhomecareapp.CustomerPost;
import com.example.inhomecareapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CaregiverHome extends AppCompatActivity {

    List<CustomerPost> customerPostList = new ArrayList<>();
    CustomerPostsAdapter customerPostsAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_home);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recycler_view);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.profile) {
                    Intent intent = new Intent(CaregiverHome.this, CaregiverProfileActivity.class);
                    startActivity(intent);
                    return true;

                } else if (id == R.id.logout) {
                    firebaseAuth.signOut();
                    Toast.makeText(CaregiverHome.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }


        });

        getPosts();
    }

    private void getPosts() {
        FirebaseFirestore.getInstance().collection("posts")
                .whereEqualTo("accept", false)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        customerPostList.clear();

                        for (QueryDocumentSnapshot snapshot : value) {
                            CustomerPost customerPost = snapshot.toObject(CustomerPost.class);
                            customerPostList.add(customerPost);
                        }

                        customerPostsAdapter = new CustomerPostsAdapter(customerPostList);
                        recyclerView.setAdapter(customerPostsAdapter);
                    }
                });
    }


}