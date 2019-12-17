package com.example.bank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabBills extends Fragment implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {


    Spinner accountTypes;
    RadioGroup bType;
    RadioButton hydro;
    RadioButton water;
    RadioButton mobile;
    RadioButton gas;
    EditText billNo;
    Spinner accType;
    TextView billDetails;

    public tabBills() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_bills, container, false);
        billNo = v.findViewById(R.id.billNo);
        accType = v.findViewById(R.id.accountType);
        bType = v.findViewById(R.id.billT);
        hydro = v.findViewById(R.id.hydro);
        water = v.findViewById(R.id.water);
        mobile = v.findViewById(R.id.mobile);
        gas = v.findViewById(R.id.gas);
        bType.setOnCheckedChangeListener(this);
        hydro.setOnCheckedChangeListener(this);
        water.setOnCheckedChangeListener(this);
        mobile.setOnCheckedChangeListener(this);
        gas.setOnCheckedChangeListener(this);
        billDetails = v.findViewById(R.id.billDetails);


        String[] accList = {"Chequing", "Saving"};
        accountTypes = v.findViewById(R.id.accountType);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, accList);
        catAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        accountTypes.setAdapter(catAdapter);


        return v;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        Bill b = new Bill();
        if(hydro.isChecked()){
            b=findBill("Hydro");
        }
        else if(gas.isChecked()){
            b=findBill("Gas");
        }
        else if(mobile.isChecked()){
            b=findBill("Mobile");
        }
        else{
            b=findBill("Water");
        }
        billDetails.setText("Bill Number: "+b.getBillNo()+" | Type: "+b.getBillType()+" | Amount: $"+b.getAmount()+" | Paid:"+b.getBillStatus());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public Bill findBill(String type){
        Bill b = new Bill();
        for( Bill bill: HomeScreen.bills) {
            if(type == bill.getBillType()){
                b = bill;
            }
        }
        return b;
    }

}
