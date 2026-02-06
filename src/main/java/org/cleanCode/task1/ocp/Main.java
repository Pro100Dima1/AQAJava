package org.cleanCode.task1.ocp;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        BitcoinPayment bitcoinPayment = new BitcoinPayment();

        paymentProcessor.processPayment(bitcoinPayment, 2.2);
    }
}
