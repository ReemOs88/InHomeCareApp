package com.example.inhomecareapp.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inhomecareapp.R;

import java.util.ArrayList;

public class post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Button button = findViewById(R.id.button);
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);
        EditText editTextTextMultiLine=findViewById(R.id.editTextTextMultiLine);


        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(post.this, android.R.layout.simple_spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);

        //submit button click event registration
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!customerAutoTV.getText().equals(null)){
                    if(!editTextTextMultiLine.getText().equals(null)){
                        Toast.makeText(post.this, "Submit", Toast.LENGTH_SHORT).show();
                    }else{
                        editTextTextMultiLine.setError("add Description");
                    }
                }else{
                    customerAutoTV.setError("select category and add Description");
                }
            }
        });

    }

    private ArrayList<String> getCustomerList()
    {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("elderly");
        customers.add("Children");
        customers.add("Special needs");

        return customers;
    }

}