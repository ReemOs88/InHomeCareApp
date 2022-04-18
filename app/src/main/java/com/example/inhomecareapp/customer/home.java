package com.example.inhomecareapp.customer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.inhomecareapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {
    ArrayList<String> ageList;
    Button button13;
    int temp;
    contract_data contract_data;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        contract_data=new contract_data();
        final AutoCompleteTextView customerAutoTV = view.findViewById(R.id.customerTextView);
        final AutoCompleteTextView age = view.findViewById(R.id.customerTextVipew);
        final AutoCompleteTextView category = view.findViewById(R.id.customerTextVPiew);
        button13 = view.findViewById(R.id.button13);
        TextInputLayout one = view.findViewById(R.id.category);
        TextInputLayout two = view.findViewById(R.id.age);
        one.setVisibility(View.INVISIBLE);
        two.setVisibility(View.INVISIBLE);
        button13.setVisibility(View.INVISIBLE);
        customerAutoTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                one.setVisibility(View.VISIBLE);
                if (position == 0) {
                    temp = 1;
                    contract_data.setHorstay("Hourly Contract");
                } else {
                    temp = 2;
                    contract_data.setHorstay("Hourly Contract");

                }


            }
        });
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    ageList = getageList(0);
                    ArrayAdapter<String> agee = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ageList);
                    age.setAdapter(agee);
                    contract_data.setState_cat("Children");
                    two.setVisibility(View.VISIBLE);
                } else if (position == 0) {
                    ageList = getageList(1);
                    ArrayAdapter<String> agee = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ageList);
                    age.setAdapter(agee);
                    two.setVisibility(View.VISIBLE);
                    contract_data.setState_cat("Eldrely");
                } else if (position == 1) {
                    ageList = getageList(2);
                    ArrayAdapter<String> agee = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ageList);
                    age.setAdapter(agee);
                    two.setVisibility(View.VISIBLE);
                    contract_data.setState_cat("people with special needs");
                }
            }
        });
        age.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                button13.setVisibility(View.VISIBLE);
                String ag =parent.getItemAtPosition(position).toString().trim();
                contract_data.setAge(ag);

            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp == 1) {
                    Intent i = new Intent(getActivity(), hourlycaregiver.class);
                    i.putExtra("obj", contract_data);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getActivity(), stayicaregive.class);
                    i.putExtra("obj", contract_data);
                    startActivity(i);
                }
            }
        });
        // create list of customer
        ArrayList<String> customerList = getCustomerList();
        ArrayList<String> categoruList = getCcategoryList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, customerList);
        ArrayAdapter<String> categoryy = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categoruList);
        //Set adapter
        customerAutoTV.setAdapter(adapter);
        category.setAdapter(categoryy);


        return view;

    }

    private void startAlert() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item, null);
        Button sat = view.findViewById(R.id.button6);
        Button sun = view.findViewById(R.id.button7);
        Button mon = view.findViewById(R.id.button8);
        Button tues = view.findViewById(R.id.button9);
        Button wed = view.findViewById(R.id.button10);
        Button thurs = view.findViewById(R.id.button11);


        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }


    private ArrayList<String> getCustomerList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Hourly Services");
        customers.add("Stay-in services");

        return customers;
    }

    private ArrayList<String> getageList(int i) {
        ArrayList<String> customers = new ArrayList<>();
        if (i == 0) {
            customers.add("0-2");
            customers.add("3-5");
            customers.add("6-12");
        } else if (i == 1) {
            customers.add("60-74");
            customers.add("greater than 74");
        } else {
            customers.add("Mental Disability");
            customers.add("Mobility and physical disabilities");
            customers.add("Sensory disability");
        }

        return customers;
    }

    private ArrayList<String> getCcategoryList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Elderly");
        customers.add("People with special needs");
        customers.add("Children");

        return customers;
    }
}