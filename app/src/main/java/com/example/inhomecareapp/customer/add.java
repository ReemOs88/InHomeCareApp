package com.example.inhomecareapp.customer;

import static android.location.LocationManager.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class add extends AppCompatActivity {
    private GpsTracker gpsTracker;
    Geocoder geocoder;
    List<Address> addresses;
    Button button4;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        firebaseAuth= FirebaseAuth.getInstance();

        TextInputEditText str,bul,floor,mobi;
        Button button5;
        str=findViewById(R.id.street_num);
        bul=findViewById(R.id.building_num);
        floor=findViewById(R.id.floo_num);
        mobi=findViewById(R.id.phon_num);
        button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference= FirebaseDatabase.getInstance().getReference().child("adress");
                addresss helperClass = new addresss(str.getText().toString().trim() ,bul.getText().toString().trim(),
                        floor.getText().toString().trim(),mobi.getText().toString().trim(),button4.getText().toString().trim(),firebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.push().setValue(helperClass);
                Toast.makeText(add.this, "Address added", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        button4=findViewById(R.id.button4);
        button4.setEnabled(true);
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions(add.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    getLocation();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                button4.setEnabled(false);
            }
        });
    }

    public void getLocation() throws IOException {
        gpsTracker = new GpsTracker(add.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            button4.setText(city + "/" + country);

        }else{
            gpsTracker.showSettingsAlert();
        }
    }
}