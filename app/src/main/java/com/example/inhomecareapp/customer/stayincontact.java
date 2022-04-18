package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class stayincontact extends AppCompatActivity {
    Button b14,b15,b17,b19;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String curDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayincontact);
        contract_data model = (contract_data) getIntent().getSerializableExtra("obj");
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        b14=findViewById(R.id.button14);
        b15=findViewById(R.id.button15);

        b17=findViewById(R.id.button17);
        b19=findViewById(R.id.button19);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(stayincontact.this,Myaddreses.class);
                startActivity(i);
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(stayincontact.this);
                View view2 = inflater.inflate(R.layout.servicestay, null);
                AlertDialog alertDialog = new AlertDialog.Builder(stayincontact.this)
                        .setView(view)
                        .create();
                Button b30=view2.findViewById(R.id.button30);
                Button b31=view2.findViewById(R.id.button31);
                Button b32=view2.findViewById(R.id.button32);
                Button b33=view2.findViewById(R.id.button33);
                b30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b30.getText().toString());
                    }
                });
                b31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b31.getText().toString());
                    }
                });
                b32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b32.getText().toString());
                    }
                });
                b33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b33.getText().toString());
                    }
                });

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });

        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(stayincontact.this);
                View view2 = inflater.inflate(R.layout.calender, null);
                AlertDialog alertDialog = new AlertDialog.Builder(stayincontact.this)
                        .setView(view)
                        .create();
                CalendarView cv= view2.findViewById(R.id.calendarView);
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month,
                                                    int dayOfMonth) {
                        curDate = String.valueOf(dayOfMonth);
                        model.setDate(curDate);
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
                DatabaseReference newUser=databaseReference.child(firebaseUser.getUid()).child("contract");
                newUser.child("horstay").setValue(model.getHorstay());
                newUser.child("state_cat").setValue(model.getState_cat());
                newUser.child("age").setValue(model.getAge());
                newUser.child("caregiver").setValue(model.getCaregiver());
                newUser.child("address").setValue(model.getAddress());
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
