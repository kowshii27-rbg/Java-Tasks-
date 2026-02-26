package com.bank.app;

public class Main{
    public static void main(String[] args){
        Account a1 = new Account("Kowshik", 5000);
        Account a2 = new Account("suraj", 8000);

        a1.displayaccount();
        a2.displayaccount();
        // accessing static variable
        System.out.println("Total Accounts Created: " + Account.accountcount);

    }
}