package org.example.task5.restaurant;

public class HotDish extends Dishes {
    private int temperature;

    HotDish(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void dishesDisplay() {
        System.out.println("Температура блюда = " + this.temperature);
    }
}
