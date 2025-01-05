package org.systemdesign.strategy.sorting;

import java.util.List;

public class SortStrategy {

    Sort sortObj;

    public List<String> customSort(List<List<String>> transactions, String strategy){
        if (strategy.equals("amount")){
            sortObj = new AmountSort();
        }
        else{
            sortObj = new TimeSort();
        }

        return sortObj.customSort(transactions);
    }
}
