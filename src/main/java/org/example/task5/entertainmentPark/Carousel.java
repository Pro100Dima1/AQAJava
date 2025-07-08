package org.example.task5.entertainmentPark;

public class Carousel extends Attraction{
    @Override
    public void feelings() {
        System.out.println("Карусели не очень быстрые, но нравятся детям");
    }

    @Override
    public void service() {
        System.out.println("На каруселях большой поток детей, поэтому им необходимо частое техническое обслуживание");
    }
}
