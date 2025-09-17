package org.expressJava.task4.cicleFor;

import java.util.Scanner;

public class SimpleNumber {
    public static void main(String[] args) {
        checkNumber();
    }

    public static boolean checkNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        boolean isPrime = true;
        for (int i = 2; i <= number - 1; i++) {
            if (number % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }
}
