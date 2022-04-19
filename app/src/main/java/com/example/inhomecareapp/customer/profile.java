package com.example.inhomecareapp.customer;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.inhomecareapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    Button button3;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private static String emailID;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button3=findViewById(R.id.button3);
        TextInputEditText name,phon,email;
        name=findViewById(R.id.nameeee);
        phon=findViewById(R.id.phoneee);
        email=findViewById(R.id.emailll);
        firebaseAuth = FirebaseAuth.getInstance().getInstance();
        database = FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        email.setText(user.getEmail());
        databaseReference= FirebaseDatabase.getInstance().getReference().child("customer");

        emailID = user.getEmail();
        DatabaseReference users = database.getReference("customer");
        final Query userQuery = users.orderByChild("email");

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    if (post.child("email").getValue().equals(firebaseAuth.getCurrentUser().getEmail())) {
                        name.setText(post.child("name").getValue(String.class));
                        phon.setText(post.child("Phon").getValue(String.class));
                    } else {
                        Log.d("Output", "Failure");
                    }

                    Log.d("Output", post.child("Email").toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("name").setValue(name.getText().toString().trim());
                databaseReference.child("phon").setValue(phon.getText().toString().trim());
                databaseReference.child("email").setValue(email.getText().toString());

            }
        });
    }
}
