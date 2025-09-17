package org.expressJava.task5.entertainmentPark;

public class Carousel extends Attraction {
    @Override
    public void feelings() {
        System.out.println("Не очень быстрый атракцион");
    }

    @Override
    public void needTO() {
        System.out.println("Требует частое техническое обслуживание");
    }
}
