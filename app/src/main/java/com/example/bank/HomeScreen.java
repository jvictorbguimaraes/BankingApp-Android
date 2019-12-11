package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabBills,tabHome,tabProfile,tabTransfers;
    PageAdapter pageAdapter;
    Client loggedClient;

    ArrayList<Account> accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);
        tabHome = findViewById(R.id.tabHome);
        tabBills = findViewById(R.id.tabBills);
        tabTransfers = findViewById(R.id.tabTransfers);
        tabProfile = findViewById(R.id.tabProfile);
        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        loggedClient = MainActivity.loggedClient;

        fillAccounts();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    pageAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 1)
                {
                    pageAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 2){
                    pageAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition() == 3){
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public void changeTab(View view){
        //viewPager.setCurrentItem(id);
        pageAdapter.notifyDataSetChanged();
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public void transfer(View view){
        EditText amount = findViewById(R.id.transferAmount);
        EditText accountNumber = findViewById(R.id.toAccount);
        Spinner sp = findViewById(R.id.transfSpinner);

        Account senderAccount = findAccountByClient(loggedClient.id, sp.getSelectedItem().toString());
        Account toAccount = findAccount(Integer.parseInt(accountNumber.getText().toString()));
        if (toAccount == null) {
            Toast.makeText(getApplicationContext(),"Account not found", Toast.LENGTH_LONG).show();
        }else if(senderAccount.amount < Double.parseDouble(amount.getText().toString())){
            Toast.makeText(getApplicationContext(),"Your account does not have this amount to transfer", Toast.LENGTH_LONG).show();
        }else{
            senderAccount.amount -= Double.parseDouble(amount.getText().toString());
            toAccount.amount += Double.parseDouble(amount.getText().toString());
            Toast.makeText(getApplicationContext(),"Transfer completed", Toast.LENGTH_LONG).show();
        }
    }

    public Account findAccountByClient(int number, String type){
        for(Account account : accounts){
            if (account.clientID == number && ((type.equals("Saving") && account instanceof Saving) || (type.equals("Chequing") && account instanceof Chequing)))
                return account;
        }
        return null;
    }

    public Account findAccount(int number){
        for(Account account : accounts){
            if (account.number == number)
                return account;
        }
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){

    }

    public void fillAccounts(){
        Account account = new Saving(12345, 0,1500.0);
        accounts.add(account);
        account = new Chequing(456187,0, 575.0);
        accounts.add(account);
        account = new Credit(879845,0,55.0, 1500.0);
        accounts.add(account);

        account = new Saving(245487, 1,512.0);
        accounts.add(account);
        account = new Chequing(518789,1, 0.0);
        accounts.add(account);
        account = new Credit(215648,1,155.0, 500.0);
        accounts.add(account);

        account = new Saving(87546, 2,2654.0);
        accounts.add(account);
        account = new Chequing(214578,2, 201.0);
        accounts.add(account);
        account = new Credit(35987,2,0.0, 1500.0);
        accounts.add(account);

        account = new Saving(254489, 3,1512.0);
        accounts.add(account);
        account = new Chequing(17787,3, 145.0);
        accounts.add(account);
        account = new Credit(67878,3,250.0, 800.0);
        accounts.add(account);
    }
}
