package org.example.task5.farm;

public class Farmer {

    private Cattle animal;

    public void setAnimal(Cattle animal) {
        this.animal = animal;
    }

    public void showInfoAboutAnimal() {
        animal.production();
        animal.needCare();
    }
}
