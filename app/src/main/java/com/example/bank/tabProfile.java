package com.example.bank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.bank.MainActivity.loggedClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabProfile extends Fragment {


    //declaring variables
    EditText name, birthday, sinExpiry, phone, email, address;
    Button update;

    public tabProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_profile, container, false);

        name = v.findViewById(R.id.name);
        birthday = v.findViewById(R.id.bday);
        sinExpiry = v.findViewById(R.id.sin);
        phone = v.findViewById(R.id.phone);
        email = v.findViewById(R.id.email);
        address = v.findViewById(R.id.address);
        update = v.findViewById(R.id.update);

        name.setText(loggedClient.getName());
        birthday.setText(loggedClient.getDob());
        sinExpiry.setText(loggedClient.getSinExp());
        phone.setText(loggedClient.getPhone());
        email.setText(loggedClient.getEmail());
        address.setText(loggedClient.getAddress());


        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loggedClient.setName(name.getText().toString());
                loggedClient.setDob(birthday.getText().toString());
                loggedClient.setSinExp(sinExpiry.getText().toString());
                loggedClient.setPhone(phone.getText().toString());
                loggedClient.setEmail(email.getText().toString());
                loggedClient.setAddress(address.getText().toString());

                name.setText(loggedClient.getName());
                birthday.setText(loggedClient.getDob());
                sinExpiry.setText(loggedClient.getSinExp());
                phone.setText(loggedClient.getPhone());
                email.setText(loggedClient.getEmail());
                address.setText(loggedClient.getAddress());

                Toast.makeText(getActivity(),"Details Updated Successfully",Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }





}
