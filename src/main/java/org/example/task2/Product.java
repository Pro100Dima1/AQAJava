package org.example.task2;

public class Product {
    String name;
    int price;

    Product(String name, int price) {
        this.price = price;
        this.name = name;
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
        System.out.println("Товар " + name + " стоит со скидкой: " + this.price);
    }
}
