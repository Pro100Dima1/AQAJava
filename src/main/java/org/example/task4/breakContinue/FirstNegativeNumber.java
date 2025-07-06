package org.example.task4.breakContinue;

import java.util.Scanner;

public class FirstNegativeNumber {
    public static void main(String[] args) {
        numberNegative();
    }

    public static void numberNegative() {
        Scanner scanner = new Scanner(System.in);
        int number;
        int sum = 0;

        while (true) {
            System.out.println("Ввдите положительное число");
            number = scanner.nextInt();
            if (number < 0) {
                System.out.println("Вы ввели отрицательноре число");
                break;
            } else {
                sum += number;
            }
        }
        System.out.println("Сумма положительных чисел = " + sum);
    }
}
