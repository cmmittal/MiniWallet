package org.systemdesign.model;

import java.util.ArrayList;

public class Wallet {
    public int balance;
    public ArrayList<Transaction> transactions;

    public Wallet(int balance, ArrayList<Transaction> transactions) {
        this.balance = balance;
        this.transactions = transactions;
    }
}
