package com.example.bank;

public class Bill {

    private String billType;
    private int billNo;
    private double amount;
    private boolean status;

    public Bill(String billType, int billNo, boolean status, double amount){
        this.billType = billType;
        this.billNo = billNo;
        this.status = status;
        this.amount = amount;
    }

    public String getBillType(){return billType;}
    public int getBillNo(){return billNo;}
    public boolean getBillStatus(){return status;}
    public double getAmount(){return amount;}

    public void setBillType(){this.billType = billType;}
    public void setBillNo(){this.billNo = billNo;}
    public void setStatus(){this.status = status;}
    public void setAmount(){this.amount = amount;}
}
