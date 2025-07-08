package org.example.task5.farm;

public class Main {
    public static void main(String[] args) {

        Cattle cow = new Cow();
        Cattle chicken = new Chicken();
        Farmer farmer = new Farmer();

        farmer.setAnimal(chicken);
        farmer.showInfoAboutAnimal();
        farmer.setAnimal(cow);
        farmer.showInfoAboutAnimal();
    }
}
