package com.example.bank;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private int account;
    private Double amount;
    private String date;
    private String type;

    public Transaction(int account, Double amount, String date) {
        this.account = account;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(Double amount, String date, String type) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
