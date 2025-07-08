package org.example.task5.entertainmentPark;

public class Main {
    public static void main(String[] args) {
        AttractionManagement manager = new AttractionManagement();
        Attraction roller = new RollerCoaster();
        Attraction carousel = new Carousel();

        manager.setAttraction(roller);
        manager.attractionManage();

        manager.setAttraction(carousel);
        manager.attractionManage();
    }
}
