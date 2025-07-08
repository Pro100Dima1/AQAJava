package org.example.task5.aquarium;

public class AquariumKeeper {

    private SeaAnimal seaAnimal;

    public void setSeaAnimal(SeaAnimal seaAnimal){
        this.seaAnimal = seaAnimal;
    }

    public void showSeaAnimalBehavior(){
        seaAnimal.behavior();
    }
}
