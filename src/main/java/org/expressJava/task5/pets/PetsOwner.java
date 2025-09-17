package org.expressJava.task5.pets;

public class PetsOwner {
    private Pets pets;

    public void setPets(Pets pets) {
        this.pets = pets;
    }

    public void animalInteraction() {
        pets.feed();
        pets.leisure();
    }
}
