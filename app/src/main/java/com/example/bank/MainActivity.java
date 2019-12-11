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
    public static Client loggedClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Client client = new Client(0  ,"emad.nasrallah","123456","Emad Nasrallah","","(437)217 2234");
        clients.add(client);
        client = new Client(1  ,"mary","456789","Mary Cris","","(425)587 5687");
        clients.add(client);
        client = new Client(2  ,"jvictor","456123","Joao Victor","","(437)567 3254");
        clients.add(client);
        client = new Client(3  ,"esha.shetty","789456","Esha Shetty","","(435)858 7688");
        clients.add(client);


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
            loggedClient = client;
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
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
