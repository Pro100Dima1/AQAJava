package org.example.task5.museum;

public class Main {
    public static void main(String[] args) {
        MuseumKeeper manager = new MuseumKeeper();
        Manuscript manuscript = new Manuscript();
        Sculpture sculpture = new Sculpture();

        manager.setExhibits(manuscript);
        manager.museumManage();

        manager.setExhibits(sculpture);
        manager.museumManage();
    }
}
