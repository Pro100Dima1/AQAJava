package org.cleanCode.task1.kiss;

public class DiscountCalculator {
    public double calculateDiscount(double price, boolean isLoyalCustomer, boolean isFirstPurchase, boolean hasCoupon) {

        double rate = 0.02;

        if (isLoyalCustomer) {
            rate = isFirstPurchase ? 0.10 : 0.05;
        } else if (hasCoupon) {
            rate = 0.07;
        }

        return price * (1 - rate);
    }
}
