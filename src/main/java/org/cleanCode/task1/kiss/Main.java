package org.cleanCode.task1.kiss;

public class Main {
    public static void main(String[] args) {
        DiscountCalculator discountCalculator = new DiscountCalculator();
        System.out.println(discountCalculator.calculateDiscount(2.8, true, true, true));
    }
}
