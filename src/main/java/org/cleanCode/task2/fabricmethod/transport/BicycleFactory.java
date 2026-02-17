package org.cleanCode.task2.fabricmethod.transport;

public class BicycleFactory extends TransportFactory {

    @Override
    Transport createTransport() {
        return new Bicycle();
    }
}
