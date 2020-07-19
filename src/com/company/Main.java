package com.company;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockedItems temp = new StockedItems("bread", 1, 100);
        stockList.addStock(temp);

        temp = new StockedItems("butter", 2.5, 50);
        stockList.addStock(temp);

        temp = new StockedItems("cola", 1.5, 35);
        stockList.addStock(temp);

        temp = new StockedItems("duck", 15, 7);
        stockList.addStock(temp);

        temp = new StockedItems("eggs", 1.2, 30);
        stockList.addStock(temp);

        temp = new StockedItems("fish", 5, 20);
        stockList.addStock(temp);

        temp = new StockedItems("garlic", 0.3, 75);
        stockList.addStock(temp);

        temp = new StockedItems("jam", 3, 15);
        stockList.addStock(temp);

        temp = new StockedItems("ketchup", 2, 31);
        stockList.addStock(temp);

        temp = new StockedItems("lemon", 0.5, 45);
        stockList.addStock(temp);


        System.out.println(stockList);
        for (String s: stockList.Items().keySet()){
            System.out.println(s);
        }

        Basket timBasket = new Basket("Tim");

        sellItem(timBasket, "butter", 1);
        sellItem(timBasket, "butter", 1);
        sellItem(timBasket, "jam", 3);
        sellItem(timBasket, "car", 2);

        System.out.println(timBasket);
        System.out.println(stockList);

    }

    public static int sellItem(Basket basket, String item, int quantity){
        StockedItems stockedItems = stockList.get(item);
        if (stockedItems == null){
            System.out.println("We don't sell " + item + "s");
            return 0;
        }
        if (stockList.sellStock(stockedItems, quantity) != 0){
            basket.addToBasket(stockedItems, quantity);
            return quantity;
        }
        return 0;
    }
}
