package org.example.task5.farm;

public class Chicken extends Cattle {
    @Override
    public void production() {
        System.out.println("Курица несёт яйца");
    }

    @Override
    public void needCare() {
        System.out.println("Курица требует зерно");
    }
}
