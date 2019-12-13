package com.example.bank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static com.example.bank.MainActivity.loggedClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabBills extends Fragment {


    Spinner accountTypes;
    Button payBill;
    EditText billNo;

    public tabBills() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_bills, container, false);


        String[] accList = {"Chequing", "Saving"};
        accountTypes = v.findViewById(R.id.accountType);
//        payBill = v.findViewById(R.id.payBill);
//        billNo = v.findViewById(R.id.billNo);
//        Bill bill;

        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, accList);
        catAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        accountTypes.setAdapter(catAdapter);



        return v;
    }


}