package com.company;

import java.util.*;

public class StockList {
    private final Map<String, StockedItems> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockedItems item){
        if (item != null){
            StockedItems onList = list.getOrDefault(item.getName(), item);
            if (onList != item){
                item.adjustStock(onList.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(StockedItems items, int quantity){
        StockedItems onList = list.getOrDefault(items.getName(), null);
        if (onList != null && onList.quantityInStock() >= quantity && quantity > 0){
            onList.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public int reserveStock(StockedItems items, int quantity){
        StockedItems onList = list.getOrDefault(items.getName(), null);
        if (onList != null && onList.quantityInStock() >= quantity && quantity > 0){
            onList.reserveStock(quantity);
            return quantity;
        }
        return 0;
    }

    public StockedItems get(String key){
        return list.get(key);
    }

    public Map<String, StockedItems> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;

        for (Map.Entry<String, StockedItems> item : list.entrySet()){
            StockedItems stockedItems = item.getValue();
            double itemValue = stockedItems.getPrice() * stockedItems.quantityInStock();

            s = s + stockedItems + ". There are " + stockedItems.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";

            totalCost += itemValue;
        }

        return s + "Total stock value: " + totalCost;
    }
}
