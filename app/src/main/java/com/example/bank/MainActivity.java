package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText username, password;

    public static ArrayList<Client> clients = new ArrayList<>();
    public static Client loggedClient;
    public static ArrayList<Account> accounts = new ArrayList<>();
    private SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Client client = new Client(0  ,"emad.nasrallah","123456","Emad Nasrallah","","(437)217 2234", "emad@gmail.com","20-5-2023","19-1-1970");
        clients.add(client);
        client = new Client(1  ,"mary","456789","Mary Cris","Scarborough","(425)587 5687","mcrismcllns@gmail.com","20-8-2021","10-5-1988");
        clients.add(client);
        client = new Client(2  ,"jvictor","456123","Joao Victor","Scarborough","(437)567 3254","jvictorbguimaraes@gmail.com","16-8-2021","2-8-1986");
        clients.add(client);
        client = new Client(3  ,"esha.shetty","789456","Esha Shetty","North York","(435)858 7688","esha.dshetty@gmail.com","10-8-2021","3-09-1996");
        clients.add(client);
        fillAccounts();
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
