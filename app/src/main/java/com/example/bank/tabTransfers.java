package com.example.bank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabTransfers extends Fragment {

    Spinner transfSpinner;

    public tabTransfers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab_transfers, container, false);
        transfSpinner = v.findViewById(R.id.transfSpinner);
        String[] list = {"Chequing", "Saving"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        catAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        transfSpinner.setAdapter(catAdapter);
        return v;
    }
}
