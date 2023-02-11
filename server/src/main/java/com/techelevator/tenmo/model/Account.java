package com.techelevator.tenmo.model;

public class Account {

    private int accountId;
    private int userId;
    private double balance;

    public Account(){
    }

    public Account(int accountId, int userId, double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

}
