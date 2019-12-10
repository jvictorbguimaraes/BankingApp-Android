package com.example.bank;

public class Credit extends Account{
    Double creditLimit;
    public Credit(int number, int clientID, Double amount,Double creditLimit) {
        super(clientID, number, amount);
        this.creditLimit = creditLimit;
    }
}
