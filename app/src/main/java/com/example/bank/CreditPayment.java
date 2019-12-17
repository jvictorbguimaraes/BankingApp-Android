package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreditPayment extends AppCompatActivity {

    Spinner creditSp;
    TextView creditBalance;
    EditText creditAmount;
    Account creditAccount, savingAccount, chequingAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_payment);

        creditSp = findViewById(R.id.creditSp);
        creditAmount = findViewById(R.id.creditAmount);
        creditBalance = findViewById(R.id.creditBalance);

        creditAccount = (Account) getIntent().getSerializableExtra("creditAccount");
        savingAccount = (Account) getIntent().getSerializableExtra("savingAccount");
        chequingAccount = (Account) getIntent().getSerializableExtra("chequingAccount");

        creditBalance.setText(creditAccount.amount.toString());

        String[] list = {"Chequing", "Saving"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        catAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        creditSp.setAdapter(catAdapter);
    }

    public void payCredit(View view){

        if(creditAmount.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Enter the amount", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent=new Intent();
        if(creditSp.getSelectedItem().toString() == "Chequing"){
            if(Double.parseDouble(creditAmount.getText().toString()) > chequingAccount.amount){
                Toast.makeText(getApplicationContext(),"Your account cannot transfer this amount", Toast.LENGTH_LONG).show();
                return;
            }
            intent.putExtra("type","Chequing");
        }else if(creditSp.getSelectedItem().toString() == "Saving"){
            if(Double.parseDouble(creditAmount.getText().toString()) > savingAccount.amount){
                Toast.makeText(getApplicationContext(),"Your account cannot transfer this amount", Toast.LENGTH_LONG).show();
                return;
            }
            intent.putExtra("type","Saving");
        }

        intent.putExtra("amount",creditAmount.getText().toString());
        setResult(2,intent);
        finish();
    }
}
