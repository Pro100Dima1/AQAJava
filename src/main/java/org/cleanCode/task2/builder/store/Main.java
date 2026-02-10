package org.cleanCode.task2.builder.store;

public class Main {
    public static void main(String[] args) {
        Order monitor = new Order.OrderBuilder()
                .setDiscount(2)
                .setPayment(10.9)
                .build();
        System.out.println(monitor);
    }
}
