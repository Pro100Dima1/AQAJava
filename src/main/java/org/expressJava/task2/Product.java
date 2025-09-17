package org.expressJava.task2;

public class Product {
    String name;
    int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return this.name;
    }

    int getPrice() {
        return this.price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    int applyDiscount(int discount) {
        int discountPrice = (discount * this.price) / 100;
        int newPrice = this.price - discountPrice;
        setPrice(newPrice);
        return newPrice;
    }

    void printInfo() {
        System.out.println("Товар " + this.name + " стоит со скидкой: " + this.price);
    }


}
