package org.example.task4.cicleFor;

import javax.swing.*;
import java.util.Scanner;

public class SimpleNumber {
    public static void main(String[] args) {
        System.out.println("Введенное значение является простым? " + checkNumber());

    }

    public static boolean checkNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        boolean isPrime = true;
        if (number % 2 == 0) {
            isPrime = false;
        }
        return isPrime;
    }
}
