package com.example.bank;

public class Credit extends Account{
    Double creditLimit;
    public Credit(int number, int clientID, Double amount,Double creditLimit) {
        super(number, clientID, amount);
        this.creditLimit = creditLimit;
    }
}
