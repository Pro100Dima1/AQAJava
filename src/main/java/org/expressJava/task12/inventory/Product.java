package org.expressJava.task12.inventory;

public class Product {
    private final int price;
    private final String name;
    private final String category;

    public Product(int price, String name, String category) {
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
