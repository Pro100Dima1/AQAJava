package org.expressJava.task5.restaurant;

public class Menu {
    private Dishes dish;

    public void setDish(Dishes dish) {
        this.dish = dish;
    }

    public void showDishesDescription() {
        dish.displayInfoDishes();
    }
}
