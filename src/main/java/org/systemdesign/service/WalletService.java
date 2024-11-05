package org.systemdesign.service;

import org.systemdesign.model.Transaction;
import org.systemdesign.model.User;
import org.systemdesign.payments.PaymentMethod;
import org.systemdesign.payments.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WalletService {
    public UserService userService;

    public WalletService(UserService userService) {
        this.userService = userService;
    }

    public String topUpWallet(String userName, String payment, int amount){
        PaymentStrategy paymentStrategy = new PaymentStrategy();
        PaymentMethod paymentMethod = paymentStrategy.getPaymentMethod(payment);
        for (var user : userService.users){
            if (user.userName.equals(userName)){
                paymentMethod.loadMoney(user, amount);
                return userName + "'s Wallet has credited with " + amount + " Rs Successfully";
            }
        }
        return "User " + userName + " is not registered";
    }

    public String fetchBalance(String userName){
        for (var user : userService.users) {
            if (user.userName.equals(userName)) {
                int userBalance = user.wallet.balance;
                return userName + "'s wallet has " + userBalance + " amount";
            }
        }
        return  "User " + userName + " is not registered";
    }

    public String sendMoney(String sender, String receiver, int amount){
        User senderUser = null, receiverUser = null;
        for (var user : userService.users){
            if (user.userName.equals(sender)){
                senderUser = user;
            } else if (user.userName.equals(receiver)) {
                receiverUser = user;
            }
        }
        if (senderUser == null){
            return "User " + sender + " is not registered to Send Money";
        }
        if (receiverUser == null){
            return "User " + receiver + " is not registered to receive Money";
        }

        Transaction transaction = new Transaction(sender, receiver, amount);
        senderUser.wallet.transactions.add(transaction);
        if (senderUser.wallet.balance >= amount) {
            senderUser.wallet.balance -= amount;

            receiverUser.wallet.balance += amount;
            receiverUser.wallet.transactions.add(transaction);
        }
        else {
            return sender + " doesn’t have sufficient amount to transfer " + amount + " Rs";
        }

        return sender + " has transferred " + amount + " Rs amount to " + receiver;
    }

    public ArrayList<String> getTransactions(String userName, String filter, String sorter){
        ArrayList<String> results = new ArrayList<>();
        for (var user : userService.users){
            if (user.userName.equals(userName)){
                ArrayList<ArrayList<String>> transactions = new ArrayList<>();
                switch (filter){
                    case "send":
                        for (var transaction : user.wallet.transactions){
                            if (transaction.sender.equals(userName)){
                                ArrayList<String> details = new ArrayList<>();
                                details.add(transaction.sender);
                                details.add(transaction.receiver);
                                details.add(String.valueOf(transaction.amount));
                                transactions.add(details);
                            }
                        }
                        break;
                    case "receive":
                        for (var transaction : user.wallet.transactions) {
                            if (transaction.receiver.equals(userName)) {
                                ArrayList<String> details = new ArrayList<>();
                                details.add(transaction.sender);
                                details.add(transaction.receiver);
                                details.add(String.valueOf(transaction.amount));
                                transactions.add(details);
                            }
                        }
                        break;
                }

                switch (sorter){
                    case "amount":
                        transactions.sort(Comparator.comparingInt(o -> Integer.parseInt(o.get(2))));
                        for (var transaction : transactions){
                            results.add(transaction.get(0) + " -> " + transaction.get(1) + " : " + transaction.get(2) + " Rs");
                        }
                        break;
                    case "time":
                        for (var transaction : transactions){
                            results.add(transaction.get(0) + " -> " + transaction.get(1) + " : " + transaction.get(2) + " Rs");
                        }
                        break;
                }
            }
        }
        return results;
    }
}
