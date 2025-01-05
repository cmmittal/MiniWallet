package org.systemdesign.strategy.sorting;

import java.util.ArrayList;
import java.util.List;

public class TimeSort implements Sort{

    @Override
    public List<String> customSort(List<List<String>> transactions){
        List<String> results = new ArrayList<>();
        for (var transaction : transactions) {
            results.add(transaction.get(0) + " -> " + transaction.get(1) + " : " + transaction.get(2) + " Rs");
        }

        return results;
    }
}
