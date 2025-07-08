package org.example.task5.restaurant;

public class Drink extends Dishes {
    private int volume;

    Drink(int volume) {
        this.volume = volume;
    }

    @Override
    public void dishesDisplay() {
        System.out.println("Объем напитка = " + this.volume);
    }
}
