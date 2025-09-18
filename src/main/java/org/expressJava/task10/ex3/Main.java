package org.expressJava.task10.ex3;

public class Main {
    public static void main(String[] args) {

    }

    public static String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }
}
