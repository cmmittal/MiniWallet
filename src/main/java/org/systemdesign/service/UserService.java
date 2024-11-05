package org.systemdesign.service;

import org.systemdesign.model.Transaction;
import org.systemdesign.model.User;
import org.systemdesign.model.Wallet;

import java.util.ArrayList;

public class UserService {
    public ArrayList<User> users = new ArrayList<>();
    private static UserService userService;
    private UserService(){}

    public static UserService getUserService(){
        if (userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public String registerUser(String userName){
        User user = new User(userName, new Wallet(0, new ArrayList<>()));
        this.users.add(user);
        return "User " + userName + " is registered";
    }
}
