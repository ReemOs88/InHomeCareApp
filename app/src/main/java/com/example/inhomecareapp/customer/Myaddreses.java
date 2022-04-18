package com.example.inhomecareapp.customer;


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

import java.util.ArrayList;

public class Myaddreses extends AppCompatActivity {
    RecyclerView recyclerView ;
    ArrayList<data> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddreses);
        Button button2=findViewById(R.id.button2);
       /* recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Myaddreses.this);
        recyclerView.setHasFixedSize(true);
        data = new ArrayList<data>();
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter order_adapter= new adapter(Myaddreses.this,  data);
        recyclerView.setAdapter(order_adapter);*/
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myaddreses.this,add.class);
                startActivity(i);
            }
        });
    }

}