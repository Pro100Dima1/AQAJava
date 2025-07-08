package org.example.task5.pets;

public class PetsOwner {
    private Pets pet;

    public void setPet(Pets pet) {
        this.pet = pet;
    }

    public void interactionWithAPet() {
        pet.feed();
        pet.leisure();
    }
}
