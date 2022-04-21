package com.example.inhomecareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
      EditText enterEmailEt;
      Button  enterPasswordBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
       enterEmailEt=findViewById(R.id.enter_email);
       enterPasswordBtn=findViewById(R.id.enter_password);
       enterPasswordBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              String customerEmail= enterEmailEt.getText().toString();
               if (customerEmail.isEmpty() ) {
                   Toast.makeText(ForgetPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                   return;
               }
               resetPassword(customerEmail);
           }
       });
    }

    private void resetPassword(String email){
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()){
                   Toast.makeText(ForgetPasswordActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                   finish();
               }
            }
        });
    }
}