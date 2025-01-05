package org.systemdesign.strategy.filter;

import org.systemdesign.model.Transaction;

import java.util.List;

public interface Filter {

    public List<List<String>> filter(String userName, List<Transaction> transactions);
}
