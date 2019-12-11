package com.example.bank;

public class Account {
    int number;
    int clientID;
    Double amount;

    public Account(int number, int clientID, Double amount) {
        this.number = number;
        this.clientID = clientID;
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
