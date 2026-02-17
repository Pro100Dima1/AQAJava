package org.cleanCode.task2.fabricmethod.transport;

public class CarFactory extends TransportFactory {

    @Override
    Transport createTransport() {
        return new Car();
    }
}
