package org.expressJava.task5.aquarium;

public class Main {
    public static void main(String[] args) {

        AquariumKeeper aquariumKeeper = new AquariumKeeper();
        Shark whiteShark = new Shark();
        StarFish patrik = new StarFish();

        aquariumKeeper.setSeaAnimal(whiteShark);
        aquariumKeeper.showInfoAboutSeaAnimal();

        aquariumKeeper.setSeaAnimal(patrik);
        aquariumKeeper.showInfoAboutSeaAnimal();
    }
}
