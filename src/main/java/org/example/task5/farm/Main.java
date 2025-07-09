package org.example.task5.farm;

public class Main {
    public static void main(String[] args) {
        Farmer farmer = new Farmer();
        Chicken chicken = new Chicken();
        Cow cow = new Cow();

        farmer.setCattle(chicken);
        farmer.showInfoAboutCattle();

        farmer.setCattle(cow);
        farmer.showInfoAboutCattle();
    }
}
