package com.example.bank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabHome extends Fragment {


    public tabHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_home, container, false);
    }

    public void test(View view){
        HomeScreen activity = (HomeScreen) getActivity();
        ArrayList<Account> accounts = activity.getAccounts();

        for(Account account : accounts){
            Double a = account.amount;
        }
    }
}
