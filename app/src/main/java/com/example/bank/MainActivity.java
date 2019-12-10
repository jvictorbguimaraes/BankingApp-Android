package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText username, password;

    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Account> accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Client client = new Client(0  ,"emad.nasrallah","123456","Emad Nasrallah","","(437)217 2234");
        clients.add(client);
        client = new Client(1  ,"mary.chris","456789","Mary Cris","","(425)587 5687");
        clients.add(client);
        client = new Client(2  ,"jvictor","456123","Joao Victor","","(437)567 3254");
        clients.add(client);
        client = new Client(3  ,"esha.shetty","789456","Esha Shetty","","(435)858 7688");
        clients.add(client);

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

    public void login(View v)
    {
        String userEntry = username.getText().toString();
        String passEntry = password.getText().toString();
        Client client = searchUser(userEntry,passEntry);
        if(username.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Insert a username",Toast.LENGTH_LONG).show();
        else if(password.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Insert a password",Toast.LENGTH_LONG).show();
        else if(client == null)
            Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_LONG).show();
        else{
            // Move to the next screeen
            //clientName = client.getName();
            //Intent intent = new Intent(this, Bank.class);
            //startActivity(intent);
        }
    }

    public Client searchUser(String user, String pass)
    {
        for (Client client : clients)
            if(user.equals(client.getUsername()) && pass.equals(client.getPassword())){
                return client;
            }
        return null;
    }
}
