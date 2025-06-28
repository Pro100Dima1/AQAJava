package org.example.task2;

public class Main {
    public static void main(String[] args) {
        Car bmw = new Car("BMW", 1997);
        Car nissan = new Car("Nissan", 2003);

        bmw.print();
        nissan.print();

        bmw.setBrand("Mersedec");
        nissan.setYear(1999);
        bmw.print();
        nissan.print();
    }
}
