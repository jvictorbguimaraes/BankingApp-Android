package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivities extends AppCompatActivity {

    ListView translist;
    TextView txtAccType;

    List<String> ts = new ArrayList<String>();
    String aa="",a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_activities);

        translist = findViewById(R.id.translist);
        txtAccType = findViewById(R.id.txtAccType);

        ArrayList<Account> accounts = (ArrayList<Account>) getIntent().getSerializableExtra("accnts");

        aa = getIntent().getExtras().getString("accntname");
        if(getIntent().getExtras().getString("btn").equals("btnCheq")) {
            ts.clear();
            txtAccType.setText("  Chequing Transaction History");
            for (Account acc : accounts) {
                if (acc instanceof Chequing) {
                    if(getIntent().getExtras().getString("accntname").equals(String.valueOf(acc.clientID))){
                        for(Transaction trans: acc.transactions){
                            if(trans.getType() != null && !trans.getType().equals("")){
                                ts.add(trans.getType() +":         "+ trans.getDate() +"         $"+ trans.getAmount());
                            }
                            else if(trans.getAccount()==0)
                            {
                                ts.add("Bill Payment:          "+ trans.getDate() +"         $"+ trans.getAmount());
                            }
                            else{
                                ts.add("Transfer:         "+ trans.getAccount()+"         "+ trans.getDate()+"         $"+ trans.getAmount());
                            }
                        }
                    }
                }
            }
        }else if (getIntent().getExtras().getString("btn").equals("btnSaving")){
            txtAccType.setText("  Saving Transaction History");
            ts.clear();
            for (Account acc : accounts) {
                if (acc instanceof Saving) {
                    if(getIntent().getExtras().getString("accntname").equals(String.valueOf(acc.clientID))){
                        for(Transaction trans: acc.transactions){
                            if(trans.getType() != null && !trans.getType().equals("")){
                                ts.add(trans.getType() +":         "+ trans.getDate() +"         $"+ trans.getAmount());
                            }
                            else if(trans.getAccount()==0)
                            {
                                ts.add("Bill Payment:         "+ trans.getDate() +"         $"+ trans.getAmount());
                            }
                            else{
                                ts.add("Transfer:         "+ trans.getAccount()+"         "+ trans.getDate()+"         $"+ trans.getAmount());
                            }

                        }
                    }
                }
            }
        }else{
            txtAccType.setText("  Credit Transaction History");
            ts.clear();
            for (Account acc : accounts) {
                if (acc instanceof Credit) {
                    if(getIntent().getExtras().getString("accntname").equals(String.valueOf(acc.clientID))){
                        for(Transaction trans: acc.transactions){
                            ts.add(trans.getType() + "         " + trans.getDate() + "         $" + trans.getAmount());
                        }
                    }
                }
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ts);
        translist.setAdapter(arrayAdapter);

    }
}
