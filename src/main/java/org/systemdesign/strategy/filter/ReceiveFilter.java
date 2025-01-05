package org.systemdesign.strategy.filter;

import org.systemdesign.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReceiveFilter implements Filter{

    @Override
    public List<List<String>> filter(String userName, List<Transaction> transactions){
        List<List<String>> txns = new ArrayList<>();
        for (var transaction : transactions) {
            if (transaction.receiver.equals(userName)) {
                ArrayList<String> details = new ArrayList<>();
                details.add(transaction.sender);
                details.add(transaction.receiver);
                details.add(String.valueOf(transaction.amount));
                txns.add(details);
            }
        }

        return txns;
    }
}
