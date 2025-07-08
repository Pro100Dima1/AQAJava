package org.example.task5.zoo;

public class Zookeeper {
    private Animal animal;

    public void showAnimalBehavior() {
        animal.move();
        animal.sound();
    }

    public void addAnimal(Animal animal) {
        this.animal = animal;
    }
}
