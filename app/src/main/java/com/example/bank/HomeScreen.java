package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabBills,tabHome,tabProfile,tabTransfers;
    PageAdapter pageAdapter;
    Client loggedClient;
    ArrayList<Client> clients;

    ArrayList<Account> accounts;

    public static Boolean checkFirst;

    private SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Button btnCheq = (Button) findViewById(R.id.btnCheq);
        //Button btnSaving =(Button) findViewById(R.id.btnSaving);

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

        //if(checkFirst == null){
            checkFirst = true;
            accounts = new ArrayList<>();
            fillAccounts();
       // }

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

        if(accountNumber.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(),"Enter the account number", Toast.LENGTH_LONG).show();
            return;
        }else if(amount.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Enter the amount", Toast.LENGTH_LONG).show();
            return;
        }

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
            senderAccount.transactions.add(new Transaction(toAccount.number, -finalAmount, f.format(new Date())));
            toAccount.amount += Double.parseDouble(amount.getText().toString());
            toAccount.transactions.add(new Transaction(senderAccount.number, Double.parseDouble(amount.getText().toString()), f.format(new Date())));
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
            if (account.clientID == number && ((type.equals("Saving") && account instanceof Saving) || (type.equals("Chequing") && account instanceof Chequing) || (type.equals("Credit") && account instanceof Credit)))
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
        account = new Credit(879845,0,437.95, 1500.0);
        account.transactions.add(new Transaction(-7.25,f.format(new Date()),"Tim Hortons"));
        account.transactions.add(new Transaction(-425.50,f.format(new Date()),"IKEA"));
        account.transactions.add(new Transaction(-5.20,f.format(new Date()),"Tim Hortons"));
        accounts.add(account);

        account = new Saving(245487, 1,512.0);
        accounts.add(account);
        account = new Chequing(518789,1, 0.0);
        accounts.add(account);
        account = new Credit(215648,1,437.95, 500.0);
        account.transactions.add(new Transaction(-7.25,f.format(new Date()),"Tim Hortons"));
        account.transactions.add(new Transaction(-425.50,f.format(new Date()),"IKEA"));
        account.transactions.add(new Transaction(-5.20,f.format(new Date()),"Tim Hortons"));
        accounts.add(account);

        account = new Saving(87546, 2,2654.0);
        accounts.add(account);
        account = new Chequing(214578,2, 201.0);
        accounts.add(account);
        account = new Credit(35987,2,437.95, 1500.0);
        account.transactions.add(new Transaction(-7.25,f.format(new Date()),"Tim Hortons"));
        account.transactions.add(new Transaction(-425.50,f.format(new Date()),"IKEA"));
        account.transactions.add(new Transaction(-5.20,f.format(new Date()),"Tim Hortons"));
        accounts.add(account);

        account = new Saving(254489, 3,1512.0);
        accounts.add(account);
        account = new Chequing(17787,3, 145.0);
        accounts.add(account);
        account = new Credit(67878,3,437.95, 800.0);
        account.transactions.add(new Transaction(-7.25,f.format(new Date()),"Tim Hortons"));
        account.transactions.add(new Transaction(-425.50,f.format(new Date()),"IKEA"));
        account.transactions.add(new Transaction(-5.20,f.format(new Date()),"Tim Hortons"));
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

    public void getTransactionsCheq(View v){
        Intent intent = new Intent(this, TransactionActivities.class);
        intent.putExtra("accnts",accounts);
        intent.putExtra("accntname",String.valueOf(loggedClient.id));
        intent.putExtra("btn","btnCheq");
        startActivity(intent);
    }
    public void getTransactionsSaving(View v){
        Intent intent = new Intent(this, TransactionActivities.class);
        intent.putExtra("accnts",accounts);
        intent.putExtra("accntname",String.valueOf(loggedClient.id));
        intent.putExtra("btn","btnSaving");
        startActivity(intent);
    }

    public void getTransactionsCredit(View v){
        Intent intent = new Intent(this, TransactionActivities.class);
        intent.putExtra("accnts",accounts);
        intent.putExtra("accntname",String.valueOf(loggedClient.id));
        intent.putExtra("btn","btnCredit");
        startActivity(intent);
    }

    public void payCredit(View view){
        Intent intent = new Intent(this, CreditPayment.class);
        intent.putExtra("creditAccount",findAccountByClient(loggedClient.id,"Credit"));
        intent.putExtra("savingAccount",findAccountByClient(loggedClient.id,"Saving"));
        intent.putExtra("chequingAccount",findAccountByClient(loggedClient.id,"Chequing"));
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && data != null)
        {
            Double amount = Double.parseDouble(data.getExtras().getString("amount"));

            if(data.getExtras().getString("type") == "Saving"){
                Account saving = findAccountByClient(loggedClient.id,"Saving");
                saving.setAmount(saving.getAmount() - amount);
                saving.transactions.add(new Transaction(amount, f.format(new Date()),"Payment"));
                TextView txtSaving = findViewById(R.id.txtSaving);
                txtSaving.setText("$" + String.format("%.2f", saving.getAmount()));
            }else{
                Account chequing = findAccountByClient(loggedClient.id,"Chequing");
                chequing.setAmount(chequing.getAmount() - amount);
                chequing.transactions.add(new Transaction(amount, f.format(new Date()),"Payment"));
                TextView txtChequing = findViewById(R.id.txtCheq);
                txtChequing.setText("$" + String.format("%.2f", chequing.getAmount()));
            }

            Account credit = findAccountByClient(loggedClient.id,"Credit");
            credit.setAmount(credit.getAmount() - amount);
            credit.transactions.add(new Transaction(amount, f.format(new Date()),"Payment"));
            TextView txtCred = findViewById(R.id.txtCred);
            txtCred.setText("$" + String.format("%.2f", credit.getAmount()));
        }
    }
}
