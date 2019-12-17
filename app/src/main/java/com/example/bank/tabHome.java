package com.example.bank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabHome extends Fragment {

    TextView txtCheq,txtSaving,txtCred,txthave,txtCreditLimit;
    int chkid=0;
    String ass ="";
    double have = 0.0;


    public tabHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_home, container, false);

        txtSaving = view.findViewById(R.id.txtSaving);
        txtCheq = view.findViewById(R.id.txtCheq);
        txtCred = view.findViewById(R.id.txtCred);
        txthave = view.findViewById(R.id.txthave);
        txtCreditLimit = view.findViewById(R.id.txtCreditLimit);
        loadTexts();

        return view;
    }

    public void loadTexts(){
        HomeScreen activity = (HomeScreen) getActivity();
        ArrayList<Account> accounts = activity.getAccounts();

        for(Account account : accounts){
            chkid = account.clientID;
            if(activity.loggedClient.id == chkid){
                if(account instanceof Saving){
                    txtSaving.setText("$" + String.format("%.2f", account.amount));
                    have = account.amount;
                }
                else if(account instanceof Chequing)
                {
                    txtCheq.setText("$" + String.format("%.2f", account.amount));
                    have += account.amount;
                }
                else if(account instanceof Credit)
                {
                    txtCred.setText("$" + String.format("%.2f", account.amount));
                    txtCreditLimit.setText("$" + ((Credit) account).creditLimit.toString());
                }
            }
        }

        txthave.setText("$" + have);
    }
}
