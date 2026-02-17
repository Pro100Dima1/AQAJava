package org.cleanCode.task2.builder.store;

public class Main {
    public static void main(String[] args) {
        Order monitor = new Order.OrderBuilder()
                .setPayment("credit card")
                .setDiscount(2.3)
                .setProduct("Monitor")
                .build();
        System.out.println(monitor);
    }
}
