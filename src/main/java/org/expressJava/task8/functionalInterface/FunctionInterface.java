package org.expressJava.task8.functionalInterface;

import java.util.function.Function;


public class FunctionInterface {
    public static void main(String[] args) {
        Function<String, Integer> lengthOfString = str -> str.length();
        System.out.println("Длинна строки = " + lengthOfString.apply("Qwerty"));
    }
}
