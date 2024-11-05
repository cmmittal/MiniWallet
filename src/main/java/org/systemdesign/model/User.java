package org.systemdesign.model;

public class User {
    public String userName;
    public Wallet wallet;

    public User(String userName, Wallet wallet) {
        this.userName = userName;
        this.wallet = wallet;
    }
}
