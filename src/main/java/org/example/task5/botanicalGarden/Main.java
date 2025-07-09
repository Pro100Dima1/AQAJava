package org.example.task5.botanicalGarden;

public class Main {
    public static void main(String[] args) {
        Gardener gardener = new Gardener();
        Cactus cactus = new Cactus();
        Orchid orchid = new Orchid();

        gardener.setPlants(cactus);
        gardener.showReqForPlants();

        gardener.setPlants(orchid);
        gardener.showReqForPlants();
    }
}
