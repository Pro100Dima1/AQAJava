package org.example.task4.dowhile;

import java.util.Scanner;

public class PositiveNumber {
    public static void main(String[] args) {
        positiveCheck();
    }

    public static void positiveCheck() {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            System.out.println("Введите положительное число!");
            number = scanner.nextInt();
        } while (number <= 0);
    }
}
