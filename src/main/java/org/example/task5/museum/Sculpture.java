package org.example.task5.museum;

public class Sculpture extends Exhibits {
    @Override
    public void historyOfExhibits() {
        System.out.println("Это скульптура на которой изображен програмист");
    }

    @Override
    public void StorageConditions() {
        System.out.println("Эта скульптура требует контроля влажности");
    }
}
