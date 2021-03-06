package com.company;

import java.util.*;

public class Basket {
    private final String name;
    private final Map<StockedItems, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new HashMap<>();
    }

    public int addToBasket(StockedItems item, int quantity){
        if(item != null && quantity != 0){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            if (quantity < 0){
                item.adjustReservedStock(quantity);
            }
            if (list.get(item) == 0)list.remove(item, 0);
            return inBasket;
        }
        return 0;
    }

    public void checkOut(){           //un-reserves items using negative quantity and removes everything reserved
        for (Map.Entry<StockedItems, Integer> reservedItem : list.entrySet()) {
            int amount = reservedItem.getValue();
            StockedItems item = reservedItem.getKey();
            item.adjustReservedStock(-amount);
            item.adjustStock(-amount);
        }
        list.clear();
    }

    public int removeFromBasket(StockedItems item, int quantity){
        if (quantity <= list.get(item)) return addToBasket(item, -quantity);
        return 0;
    }

    public Map<StockedItems, Integer> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + (list.size() == 1 ? " item" : " items") + "\n";
        double totalCost = 0;
        for (Map.Entry<StockedItems, Integer> item : list.entrySet()){
            s = s + item.getKey() + ", " + item.getValue() + " reserved\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost: " + totalCost;
    }
}
