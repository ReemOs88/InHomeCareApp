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
import android.widget.CalendarView;
import android.widget.Toast;

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

public class stayincontact extends AppCompatActivity {
    Button b14,b15,b17,b19;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String curDate;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayincontact);
        contract_data model = (contract_data) getIntent().getSerializableExtra("obj");
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        b14=findViewById(R.id.button144);
        b15=findViewById(R.id.button155);

        b17=findViewById(R.id.button177);
        b19=findViewById(R.id.button199);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(stayincontact.this);
                View view2 = inflater.inflate(R.layout.activity_myaddreses, null);
                AlertDialog alertDialog = new AlertDialog.Builder(stayincontact.this)
                        .setView(view2)
                        .create();
                Button button2=view2.findViewById(R.id.button2);
                button2.setVisibility(View.INVISIBLE);
                RecyclerView rv= findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setHasFixedSize(true);
                ArrayList<addresss>  Listdata = new ArrayList<addresss>();
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
                        adapter order_adapter= new adapter(getApplicationContext(),  Listdata);
                        rv.setAdapter(order_adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });






                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(stayincontact.this);
                View view2 = inflater.inflate(R.layout.servicestay, null);
                AlertDialog alertDialog = new AlertDialog.Builder(stayincontact.this)
                        .setView(view2)
                        .create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                Button b30=view2.findViewById(R.id.button30);
                Button b31=view2.findViewById(R.id.button31);
                Button b32=view2.findViewById(R.id.button32);
                Button b33=view2.findViewById(R.id.button33);
                b30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b30.getText().toString());
                        Toast.makeText(stayincontact.this, b30.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b31.getText().toString());
                        Toast.makeText(stayincontact.this, b31.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b32.getText().toString());
                        Toast.makeText(stayincontact.this, b32.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b33.getText().toString());
                        Toast.makeText(stayincontact.this, b33.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });


            }
        });

        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(stayincontact.this);
                View view2 = inflater.inflate(R.layout.calender, null);
                AlertDialog alertDialog = new AlertDialog.Builder(stayincontact.this)
                        .setView(view2)
                        .create();
                CalendarView cv= view2.findViewById(R.id.calendarView);
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month,
                                                    int dayOfMonth) {
                        curDate = String.valueOf(dayOfMonth);
                        Toast.makeText(stayincontact.this, curDate+"Submited", Toast.LENGTH_SHORT).show();
                        model.setDate(curDate);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                DatabaseReference newUser=databaseReference.child(user.getUid()).child("contract").push();
                newUser.child("horstay").setValue(model.getHorstay());
                newUser.child("state_cat").setValue(model.getState_cat());
                newUser.child("age").setValue(model.getAge());
                newUser.child("caregiver").setValue(model.getCaregiver());
                newUser.child("address").setValue("addresss");
                newUser.child("servise_duration").setValue(model.getServise_duration());
                newUser.child("frequentation").setValue(model.getFrequentation());
                newUser.child("date").setValue(model.getDate());

                Toast.makeText(stayincontact.this,"done",Toast.LENGTH_LONG).show();
                Intent toSigin = new Intent(stayincontact.this,home.class);
                startActivity(toSigin);
                finish();

            }
        });
    }
}