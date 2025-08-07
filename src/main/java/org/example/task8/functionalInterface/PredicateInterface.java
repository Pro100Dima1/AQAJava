package org.example.task8.functionalInterface;

import java.util.function.Predicate;

public class PredicateInterface {
    public static void main(String[] args) {
        Predicate<Integer> number = x -> x % 2 == 0;
        System.out.println("Введенное число четное? " + number.test(5));
        System.out.println("Введенное число четное? " + number.test(6));
    }
}
