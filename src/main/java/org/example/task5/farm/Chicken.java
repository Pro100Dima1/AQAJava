package org.example.task5.farm;

public class Chicken extends Cattle {

    @Override
    public void product() {
        System.out.println("Курица несет яйца");
    }

    @Override
    public void needForCare() {
        System.out.println("Курица требует зерно");
    }
}
