package org.example.task4.breakContinue;

import java.util.Scanner;

public class FirstNegativeNumber {
    public static void main(String[] args) {
        numberNegative();
    }

    public static void numberNegative() {
        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {
            System.out.println("Введите число");
            number = scanner.nextInt();
            if (number < 0) {
                System.out.println("Вы ввели отрицательное число. Конец");
                break;
            }
        }
    }
}
