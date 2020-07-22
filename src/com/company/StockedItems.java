package com.company;

public class StockedItems implements Comparable<StockedItems>{
    private final String name;
    private double price;
    private int inStock;
    private int reserved;

    public StockedItems(String name, double price, int inStock) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.reserved = 0;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int quantityInStock() {
        return inStock;
    }
    public int getReserved() {
        return reserved;
    }

    public void setPrice(double price){
        if (price > 0.0){
            this.price = price;
        }
    }

    public void adjustStock(int quantity){
        int newQuantity = this.inStock + quantity;
        if (newQuantity >= 0){
            this.inStock = newQuantity;
        }
    }

    public void reserveStock(int quantity){
        if (quantity <= inStock - reserved){
            reserved += quantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        //System.out.println("Entering StockedItems.equals");
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        String objName = ((StockedItems)obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public String toString() {
        return this.name/* + " : " + this.price*/;
    }

    @Override
    public int compareTo(StockedItems o) {
        //System.out.println("Entering StockedItems.compareTo");
        if (this == o) return 0;
        if (o != null)return this.name.compareTo(o.getName());
        throw new NullPointerException();
    }
}
