package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
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
import java.util.Date;

public class HomeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabBills,tabHome,tabProfile,tabTransfers;
    PageAdapter pageAdapter;
    Client loggedClient;
    ArrayList<Client> clients;

    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();

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
        clients = MainActivity.clients;

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
            return;
        }else if(senderAccount instanceof Saving && senderAccount.amount < Double.parseDouble(amount.getText().toString()) + 5) {
            Toast.makeText(getApplicationContext(), "Your account cannot transfer this amount", Toast.LENGTH_LONG).show();
            return;
        }else if(senderAccount.amount < Double.parseDouble(amount.getText().toString())){
            Toast.makeText(getApplicationContext(),"Your account cannot transfer this amount", Toast.LENGTH_LONG).show();
            return;
        }else{
            Double finalAmount;
            if(senderAccount instanceof Saving){
                finalAmount = Double.parseDouble(amount.getText().toString()) + 5;
            }else {
                finalAmount = Double.parseDouble(amount.getText().toString());
            }
            senderAccount.amount -= finalAmount;
            senderAccount.transactions.add(new Transaction(toAccount.number, -finalAmount, new Date()));
            toAccount.amount += Double.parseDouble(amount.getText().toString());
            toAccount.transactions.add(new Transaction(senderAccount.number, Double.parseDouble(amount.getText().toString()), new Date()));
        }

        Client toClient = findClientByAccount(toAccount.getClientID());
        sendTransferEmail(toClient, Double.parseDouble(amount.getText().toString()));

        Toast.makeText(getApplicationContext(),"Transfer completed", Toast.LENGTH_LONG).show();
    }

    public Client findClientByAccount(int number){
        for(Client client : clients){
            if (client.id == number)
                return client;
        }
        return null;
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
        account = new Chequing(456187,0, 1575.0);
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

    public void sendTransferEmail(Client toClient, Double amount){

        final String message = loggedClient.getName() + " transfered " + amount + " CAD to your account";
        final String email = toClient.getEmail();


        AsyncTask async = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try{
                    GMailSender sender = new GMailSender("juliedos19@gmail.com",
                            "chickenbariken");
                    sender.sendMail("JEM Bank Transfer", message,
                            "juliedos19@gmail.com", email);
                } catch (Exception e) {
                }
                return  null;
            }

            @Override
            protected void onPostExecute(Object o) {

            }
        }.execute();
    }
}
