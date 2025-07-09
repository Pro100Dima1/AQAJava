package org.example.task5.entertainmentPark;

public class Main {
    public static void main(String[] args) {
        AttractionManagement attractionManagement = new AttractionManagement();
        Carousel carousel = new Carousel();
        RollerCoaster rollerCoaster = new RollerCoaster();

        attractionManagement.setAttraction(carousel);
        attractionManagement.showInfoAboutAttraction();

        attractionManagement.setAttraction(rollerCoaster);
        attractionManagement.showInfoAboutAttraction();
    }
}
