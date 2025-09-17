package org.expressJava.task5.restaurant;

public class Main {
    public static void main(String[] args) {

        Menu menu1 = new Menu();
        Drink water = new Drink(100);
        HotDish spageti = new HotDish(37);

        menu1.setDish(spageti);
        menu1.showDishesDescription();

        menu1.setDish(water);
        menu1.showDishesDescription();

    }
}
