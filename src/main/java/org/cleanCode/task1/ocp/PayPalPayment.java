package org.cleanCode.task1.ocp;

public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата кредитной картой на сумму " + amount);
    }
}
