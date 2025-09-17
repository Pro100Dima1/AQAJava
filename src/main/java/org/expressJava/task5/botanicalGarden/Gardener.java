package org.expressJava.task5.botanicalGarden;

public class Gardener {

    private Plants plants;

    public void setPlants(Plants plants) {
        this.plants = plants;
    }

    public void showReqForPlants() {
        plants.careRequirements();
    }
}
