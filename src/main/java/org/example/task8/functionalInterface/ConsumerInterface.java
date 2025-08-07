package org.example.task8.functionalInterface;

import java.util.function.Consumer;

public class ConsumerInterface {
    public static void main(String[] args) {
        Consumer<String> stringToPrint = str -> System.out.println(str);
        stringToPrint.accept("Здраьсте");
    }
}
