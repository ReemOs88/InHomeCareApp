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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hourlycontract extends AppCompatActivity {
    Button b14,b15,b16,b17,b19;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String curDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourlycontract);
        b14=findViewById(R.id.button14);
        b15=findViewById(R.id.button15);
        b16=findViewById(R.id.button16);
        b17=findViewById(R.id.button17);
        b19=findViewById(R.id.button19);
        contract_data model = (contract_data) getIntent().getSerializableExtra("obj");


        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(hourlycontract.this,Myaddreses.class);
                startActivity(i);
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(hourlycontract.this);
                View view2 = inflater.inflate(R.layout.servicdration, null);
                AlertDialog alertDialog = new AlertDialog.Builder(hourlycontract.this)
                        .setView(view)
                        .create();
                Button b18=view2.findViewById(R.id.button18);
                Button b20=view2.findViewById(R.id.button20);
                Button b21=view2.findViewById(R.id.button21);
                Button b22=view2.findViewById(R.id.button22);
                Button b23=view2.findViewById(R.id.button23);
                Button b24=view2.findViewById(R.id.button24);
                Button b25=view2.findViewById(R.id.button25);
                Button b26=view2.findViewById(R.id.button26);
                Button b27=view2.findViewById(R.id.button27);
                Button b28=view2.findViewById(R.id.button28);
                b18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b18.getText().toString());
                    }
                });
                b20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b20.getText().toString());
                    }
                });
                b21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b21.getText().toString());
                    }
                });
                b22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b22.getText().toString());
                    }
                });
                b23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b23.getText().toString());
                    }
                });
                b24.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b24.getText().toString());
                    }
                });
                b25.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b25.getText().toString());
                    }
                });
                b26.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b26.getText().toString());
                    }
                });
                b27.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b27.getText().toString());
                    }
                });
                b28.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b28.getText().toString());
                    }
                });

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(hourlycontract.this);
                View view2 = inflater.inflate(R.layout.item, null);
                AlertDialog alertDialog = new AlertDialog.Builder(hourlycontract.this)
                        .setView(view)
                        .create();
                RadioGroup radioGroup= view2.findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton;
                radioButton = (RadioButton) findViewById(selectedId);

                //String frequentation=radioButton.getText().toString()+;;

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(hourlycontract.this);
                View view2 = inflater.inflate(R.layout.calender, null);
                AlertDialog alertDialog = new AlertDialog.Builder(hourlycontract.this)
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

                Toast.makeText(hourlycontract.this,"Submitted",Toast.LENGTH_LONG).show();
                Intent toSigin = new Intent(hourlycontract.this,home.class);
                startActivity(toSigin);
                finish();
            }
        });

    }

}