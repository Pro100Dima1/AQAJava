package org.expressJava.task8.functionalInterface;

import java.util.function.Predicate;

public class PredicateInterface {
    public static void main(String[] args) {
        Predicate<Integer> numbers = x -> x % 2 == 0;
        System.out.println("Введенное число четное? " + numbers.test(5));
        System.out.println("Введенное число четное? " + numbers.test(2));
    }
}
