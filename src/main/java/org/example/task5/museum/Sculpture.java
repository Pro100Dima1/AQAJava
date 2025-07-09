package org.example.task5.museum;

public class Sculpture extends Exhibits {

    @Override
    public void history() {
        System.out.println("Это скульптура древнего програмиста");
    }

    @Override
    public void storageConditions() {
        System.out.println("Скульптура — нуждается в реставрации");
    }
}
