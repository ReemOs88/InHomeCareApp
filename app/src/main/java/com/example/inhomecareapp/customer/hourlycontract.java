package com.example.inhomecareapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class hourlycontract extends AppCompatActivity {
    Button b14,b15,b16,b17,b19;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String curDate;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    final Context context = this;

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
                LayoutInflater inflater = LayoutInflater.from(hourlycontract.this);
                View view2 = inflater.inflate(R.layout.activity_myaddreses, null);
                AlertDialog alertDialog = new AlertDialog.Builder(hourlycontract.this)
                        .setView(view2)
                        .create();
                Button button2=view2.findViewById(R.id.button2);
                button2.setVisibility(View.INVISIBLE);
                RecyclerView rv= findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                ArrayList<addresss> Listdata = new ArrayList<addresss>();
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
                LayoutInflater inflater = LayoutInflater.from(hourlycontract.this);
                View view2 = inflater.inflate(R.layout.servicdration, null);
                AlertDialog alertDialog = new AlertDialog.Builder(hourlycontract.this)
                        .setView(view2)
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
                        Toast.makeText(hourlycontract.this, b18.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b20.getText().toString());
                        Toast.makeText(hourlycontract.this, b20.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b21.getText().toString());
                        Toast.makeText(hourlycontract.this, b21.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b22.getText().toString());
                        Toast.makeText(hourlycontract.this, b22.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b23.getText().toString());
                        Toast.makeText(hourlycontract.this, b23.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b24.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b24.getText().toString());
                        Toast.makeText(hourlycontract.this, b24.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b25.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b25.getText().toString());
                        Toast.makeText(hourlycontract.this, b25.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b26.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b26.getText().toString());
                        Toast.makeText(hourlycontract.this, b26.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b27.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b27.getText().toString());
                        Toast.makeText(hourlycontract.this, b27.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                b28.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        model.setServise_duration(b28.getText().toString());
                        Toast.makeText(hourlycontract.this, b28.getText().toString()+"Submited", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
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
                        .setView(view2)
                        .create();
                final String[] frequentation = new String[1];
                RadioGroup radioGroup= view2.findViewById(R.id.radioGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch(checkedId){
                            case R.id.radioButton:
                                frequentation[0] ="Morning visits";
                                break;
                            case R.id.radioButton2:
                                frequentation[0] ="Evening Visits";                                break;

                        }
                    }
                });



                Button sat,sun,mod,tu,wed,th;
                final int[] sa = {0};
                final int[] su = {0};
                final int[] mo = {0};
                final int[] t = {0};
                final int[] we = {0};
                final int[] thr = {0};



                sat=view2.findViewById(R.id.button6);
                sun=view2.findViewById(R.id.button7);
                mod=view2.findViewById(R.id.button8);
                tu=view2.findViewById(R.id.button9);
                wed=view2.findViewById(R.id.button10);
                th=view2.findViewById(R.id.button11);
                sat.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        if(sa[0] ==0){
                            Toast.makeText(hourlycontract.this, sat.getText().toString()+"Selected", Toast.LENGTH_SHORT).show();
                            sat.setBackgroundColor(R.color.teal_700);
                            sa[0] =1;
                        }else{
                            sat.setBackgroundColor(R.color.blue);
                            sa[0] =0;

                        }

                    }
                });
                sun.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        if(su[0] ==0){
                            Toast.makeText(hourlycontract.this, sun.getText().toString()+"Selected", Toast.LENGTH_SHORT).show();
                            sun.setBackgroundColor(R.color.teal_700);
                            su[0] =1;
                        }else{
                            sun.setBackgroundColor(R.color.blue);
                            su[0] =0;
                        }

                    }
                });
                mod.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        if(mo[0] ==0){
                            Toast.makeText(hourlycontract.this, mod.getText().toString()+"Selected", Toast.LENGTH_SHORT).show();
                            mod.setBackgroundColor(R.color.teal_700);
                            mo[0] =1;
                        }else{
                            mod.setBackgroundColor(R.color.blue);
                            mo[0] =0;

                        }

                    }
                });
                tu.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        if(t[0] ==0){
                            Toast.makeText(hourlycontract.this, tu.getText().toString()+"Selected", Toast.LENGTH_SHORT).show();
                            tu.setBackgroundColor(R.color.teal_700);
                            t[0] =1;
                        }else{
                            tu.setBackgroundColor(R.color.blue);
                            t[0] =0;

                        }

                    }
                });
                wed.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        if(we[0] ==0){
                            Toast.makeText(hourlycontract.this, wed.getText().toString()+"Selected", Toast.LENGTH_SHORT).show();
                            wed.setBackgroundColor(R.color.teal_700);
                            we[0] =1;
                        }else{
                            wed.setBackgroundColor(R.color.blue);
                            we[0] =0;
                        }

                    }
                });
                th.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        if(thr[0] ==0){
                            Toast.makeText(hourlycontract.this, th.getText().toString()+"Selected", Toast.LENGTH_SHORT).show();
                            th.setBackgroundColor(R.color.teal_700);
                            thr[0] =1;
                        }else{
                            th.setBackgroundColor(R.color.blue);
                            thr[0] =1;

                        }

                    }
                });

                Button button=view2.findViewById(R.id.button34);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(hourlycontract.this, frequentation[0]+"Submited", Toast.LENGTH_SHORT).show();
                        model.setFrequentation(frequentation[0]);
                    }
                });

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
                        .setView(view2)
                        .create();

                CalendarView cv= view2.findViewById(R.id.calendarView);
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month,
                                                    int dayOfMonth) {
                        curDate = String.valueOf(dayOfMonth);
                        Toast.makeText(hourlycontract.this, curDate+"Selected", Toast.LENGTH_SHORT).show();

                        model.setDate(curDate);
                    }
                });
                Button button=view2.findViewById(R.id.button29);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

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