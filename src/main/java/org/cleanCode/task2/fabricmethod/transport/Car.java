package org.cleanCode.task2.fabricmethod.transport;

public class Car extends Transport {
    @Override
    void drive() {
        System.out.println("The car is moving counting the wheels");
    }
}
