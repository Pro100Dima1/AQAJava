package org.expressJava.task2;

public class Laptop {
    String brand;
    int price;

    Laptop(String brand, int price) {
        this.price = price;
        this.brand = brand;
    }

    String getBrand(){
        return this.brand;
    }

    int getPrice(){
        return this.price;
    }

    void setPrice(int price){
        this.price = price;
    }

    void setBrand(String brand){
        this.brand = brand;
    }

    void printInfo(){
        System.out.println("Ноутбук " + this.brand + " стоит " + this.price);
    }

}
