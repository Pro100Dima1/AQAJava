package org.example.task5.entertainmentPark;

public class AttractionManagement {

    private Attraction attraction;

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public void showInfoAboutAttraction() {
        attraction.feelings();
        attraction.needTO();
    }
}
