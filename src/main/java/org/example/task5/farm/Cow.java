package org.example.task5.farm;

public class Cow extends Cattle {
    @Override
    public void production() {
        System.out.println("Корова даёт молоко");
    }

    @Override
    public void needCare() {
        System.out.println("Корова нуждается в выпасе");
    }
}
