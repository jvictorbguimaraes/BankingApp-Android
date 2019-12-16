package com.example.bank;

public class Bill {

    private String billType;
    private int billNo;
    private double amount;
    private boolean status;

    public Bill(){
        this.billType = "";
        this.billNo = 0;
        this.status = false;
        this.amount = 0.0;
    }

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

    public void setBillType(String billType){this.billType = billType;}
    public void setBillNo(int billNo){this.billNo = billNo;}
    public void setBillStatus(boolean status){this.status = status;}
    public void setAmount(double amount){this.amount = amount;}
}
