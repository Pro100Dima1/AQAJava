package org.expressJava.task5.farm;

public class Cow extends Cattle {

    @Override
    public void product() {
        System.out.println("Корова дает молоко");
    }

    @Override
    public void needForCare() {
        System.out.println("Корове нужен выпас");
    }
}
