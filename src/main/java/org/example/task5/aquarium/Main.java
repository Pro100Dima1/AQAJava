package org.example.task5.aquarium;

public class Main {
    public static void main(String[] args) {

        SeaAnimal seaAnimal = new Shark();
        SeaAnimal seaAnimal1 = new StarFish();
        AquariumKeeper employee1 = new AquariumKeeper();

        employee1.setSeaAnimal(seaAnimal);
        employee1.showSeaAnimalBehavior();

        employee1.setSeaAnimal(seaAnimal1);
        employee1.showSeaAnimalBehavior();
    }
}
