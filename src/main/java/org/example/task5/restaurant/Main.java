package org.example.task5.restaurant;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        HotDish spageti = new HotDish(37);
        Drink water = new Drink(100);

        menu.setDish(spageti);
        menu.printDishInfo();

        menu.setDish(water);
        menu.printDishInfo();
    }
}
