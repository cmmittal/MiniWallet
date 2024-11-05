package org.systemdesign;

import org.systemdesign.service.UserService;
import org.systemdesign.service.WalletService;

public class Main {
    public static void main(String[] args) {

        UserService userService = UserService.getUserService();
        WalletService walletService = new WalletService(userService);

        System.out.println(walletService.fetchBalance("Bob"));
        System.out.println(userService.registerUser("Bob"));
        System.out.println(walletService.topUpWallet("Bob", "CC", 1000));
        System.out.println(walletService.topUpWallet("Bob", "UPI", 100));
        System.out.println(walletService.fetchBalance("Bob"));
        System.out.println();
        System.out.println(userService.registerUser("Alice"));
        System.out.println(walletService.topUpWallet("Alice", "CC", 100));
        System.out.println(walletService.fetchBalance("Alice"));
        System.out.println();
        System.out.println(walletService.sendMoney("Bob", "Alice", 1250));
        System.out.println(walletService.sendMoney("Bob", "Alice", 250));
        System.out.println(walletService.sendMoney("Alice", "Bob", 50));
        System.out.println();
        System.out.println(walletService.fetchBalance("Bob"));
        System.out.println(walletService.fetchBalance("Alice"));
        System.out.println();
        System.out.println(walletService.getTransactions("Bob", "send", "amount"));
        System.out.println(walletService.getTransactions("Bob", "receive", "time"));
    }
}