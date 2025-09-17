package org.expressJava.task5.pets;

public class Main {
    public static void main(String[] args) {
        PetsOwner owner = new PetsOwner();
        Pets pitbul = new Dog();
        Cat spynx = new Cat();

        owner.setPets(pitbul);
        owner.animalInteraction();

        owner.setPets(spynx);
        owner.animalInteraction();
    }
}
