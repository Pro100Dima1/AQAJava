package org.example.task5.botanicalGarden;

public class Main {
    public static void main(String[] args) {
        Plants cactus = new Cactus();
        Plants orchid = new Orchid();
        Gardener gardener = new Gardener();

        gardener.setPlant(cactus);
        gardener.showNecessaryCare();

        gardener.setPlant(orchid);
        gardener.showNecessaryCare();
    }
}
