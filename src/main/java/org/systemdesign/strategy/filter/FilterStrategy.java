package org.systemdesign.strategy.filter;

import org.systemdesign.model.Transaction;

import java.util.List;

public class FilterStrategy {

    Filter filter;

    public List<List<String>> filter(String userName, List<Transaction> transactions, String base){
        if (base.equals("send")){
            filter = new SendFilter();
        }
        else {
            filter = new ReceiveFilter();
        }

        return filter.filter(userName, transactions);
    }
}
