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
        System.out.println();
        for (StockedItems s: stockList.Items().values()){
            System.out.println(s);
        }

        Basket timBasket = new Basket("Tim");

        System.out.println();
        addToCart(timBasket, "butter", 1);
        addToCart(timBasket, "butter", 1);
        addToCart(timBasket, "jam", 3);
        addToCart(timBasket, "car", 2);

        System.out.println(timBasket);

        System.out.println();
        removeFromCart(timBasket, "jam", 2);
        removeFromCart(timBasket, "butter", 3);

        System.out.println(stockList);

        payForProduct(timBasket);

        System.out.println(timBasket);          //checks if the basket is now empty

        System.out.println(stockList);

    }

    public static int addToCart(Basket basket, String item, int quantity){       //slightly altered version of the sellStock() method to fit its purpose
        StockedItems stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We don't sell " + item + "s");
            return 0;
        }
        if (stockList.reserveStock(stockItem, quantity) != 0){
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
    public static int removeFromCart(Basket basket, String item, int quantity){
        StockedItems stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We don't sell " + item + "s");
            return 0;
        }
        if (stockItem.getReserved() >= quantity){
            basket.removeFromBasket(stockItem, quantity);
            return quantity;
        }
        System.out.println("You do not have that many products in your basket");
        return 0;
    }

    public static void payForProduct(Basket basket){
        System.out.println(basket);
        System.out.println("Proceeding with check-out...");
        basket.checkOut();
        System.out.println("Thank you for shopping with us!");
    }
}
