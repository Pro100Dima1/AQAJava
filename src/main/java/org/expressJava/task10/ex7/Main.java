package org.expressJava.task10.ex7;

public class Main {
    public static void main(String[] args) {

    }

    public static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative numbers not allowed");
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
}
