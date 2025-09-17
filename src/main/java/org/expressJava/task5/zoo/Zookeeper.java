package org.expressJava.task5.zoo;

public class Zookeeper {
    private Animal animal;

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void showBehaviourAnimal() {
        animal.sound();
        animal.walk();
    }
}
