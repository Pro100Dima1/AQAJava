package org.example.task5.museum;

public class MuseumKeeper {

    private Exhibits exhibits;

    public void setExhibits(Exhibits exhibits) {
        this.exhibits = exhibits;
    }

    public void showExhibits() {
        exhibits.history();
        exhibits.storageConditions();
    }
}
