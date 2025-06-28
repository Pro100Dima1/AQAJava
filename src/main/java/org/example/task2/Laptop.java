package org.example.task2;

public class Laptop {
    String brand;
    int price;

    Laptop(String brand, int price){
        this.brand = brand;
        this.price = price;
    }
    String getBrand(){
        return this.brand;
    }
    void setPrice(int price){
        this.price = price;
    }
    int getPrice(){
        return this.price;
    }
    void printInfo(){
        System.out.println("Ноутбук марки " + this.brand + " стоит: " + this.price);
    }

}
