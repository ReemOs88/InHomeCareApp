package com.example.inhomecareapp.customer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.inhomecareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Myaddreses extends AppCompatActivity {
    RecyclerView rv ;
    ArrayList<addresss> Listdata;
    data_adapter adds_adapter;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddreses);
        Button button2=findViewById(R.id.button2);
        rv= findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Myaddreses.this);
        rv.setHasFixedSize(true);
        Listdata = new ArrayList<addresss>();
        rv.setLayoutManager(linearLayoutManager);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref1= database.getInstance().getReference("adress");
        final Query userQuery = ref1.orderByChild("id");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.child("id").getValue().equals(user.getUid())) {
                        addresss l=snapshot.getValue(addresss.class);
                        l.setBul(post.child("bul").getValue().toString());
                        l.setFloor(post.child("floor").getValue().toString());
                        l.setId(post.child("id").getValue().toString());
                        l.setLocat(post.child("locat").getValue().toString());
                        l.setMobi(post.child("mobi").getValue().toString());
                        l.setStr(post.child("str").getValue().toString());
                        Listdata.add(l);

                    }
                }
                adapter order_adapter= new adapter(Myaddreses.this,  Listdata);
                rv.setAdapter(order_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myaddreses.this,add.class);
                startActivity(i);
            }
        });
    }

}