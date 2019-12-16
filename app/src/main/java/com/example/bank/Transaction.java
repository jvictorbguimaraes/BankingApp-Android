package com.example.bank;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private int account;
    private Double amount;
    private Date date;

    public Transaction(int account, Double amount, Date date) {
        this.account = account;
        this.amount = amount;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
