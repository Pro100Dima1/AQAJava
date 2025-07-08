package org.example.task5.botanicalGarden;

public class Gardener {
    private Plants plant;

    public void setPlant(Plants plant) {
        this.plant = plant;
    }

    public void showNecessaryCare() {
        plant.needCare();
    }
}
