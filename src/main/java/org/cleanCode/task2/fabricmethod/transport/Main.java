package org.cleanCode.task2.fabricmethod.transport;

public class Main {
    public static void main(String[] args) {
        TransportFactory transportFactory = createTransportByDriver("wheels");
        Transport transport = transportFactory.crateTransport();

        transport.drive();
    }

    static TransportFactory createTransportByDriver(String drive) {
        if (drive.equalsIgnoreCase("wheels")) {
            return new CarFactory();
        } else if (drive.equalsIgnoreCase("leg")) {
            return new BicycleFactory();
        }else {
            throw new RuntimeException(drive + "not found");
        }
    }
}
