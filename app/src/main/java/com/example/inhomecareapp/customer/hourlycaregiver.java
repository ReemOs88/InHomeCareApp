package com.example.inhomecareapp.customer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
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

public class hourlycaregiver extends AppCompatActivity {
    RecyclerView rv;
    List<CaregiverData> Listdata;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    data_adapter adds_adapter;
    public  String major;
    private static String emailID;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourlycaregiver);
        rv=findViewById(R.id.rv_hourly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        Listdata = new ArrayList<>();
        contract_data model = (contract_data) getIntent().getSerializableExtra("obj");

        database = FirebaseDatabase.getInstance();
        ref1= database.getInstance().getReference("hourly caregivers");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    String name = userSnapshot.child("name").getValue().toString();
                    String phon = userSnapshot.child("gender").getValue().toString();
                    String Address = userSnapshot.child("cate").getValue().toString();

                    CaregiverData l=userSnapshot.getValue(CaregiverData.class);
                    l=new CaregiverData(name,phon,Address);

                    Listdata.add(l);                                    }
                adds_adapter = new data_adapter(Listdata, hourlycaregiver.this,model,2);
                rv.setAdapter(adds_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });







    }


}