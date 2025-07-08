package org.example.task5.restaurant;

public class Menu {
    private Dishes dish;

    public void printDishInfo() {
        dish.dishesDisplay();
    }

    public void setDish(Dishes dish) {
        this.dish = dish;
    }

}
