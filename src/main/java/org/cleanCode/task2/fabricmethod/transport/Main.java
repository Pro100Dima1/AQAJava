package org.cleanCode.task2.fabricmethod.transport;

public class Main {
    public static void main(String[] args) {
        TransportFactory transportFactory = createTransportByDriving("legs");
        Transport transport = transportFactory.createTransport();

        transport.drive();
    }

    static TransportFactory createTransportByDriving(String drive) {
        if (drive.equalsIgnoreCase("motor")) {
            return new CarFactory();
        } else if (drive.equalsIgnoreCase("legs")) {
            return new BicycleFactory();
        } else {
            throw new RuntimeException("Transport not created");
        }
    }
}