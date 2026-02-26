package com.bank.app;

public class Account{
    // private
    private String accountholder;

    //protected
    protected double balance;

    //public
    public static int accountcount = 0;

    //constructor
    public Account(String name, double balance) {
        this.accountholder = name;
        this.balance = balance;
        accountcount++; //static counter increases
    }

    //public method
    public void displayaccount(){
        System.out.println("Account Holder: " + accountholder);
        System.out.println("Balance: " + balance);
    }
}