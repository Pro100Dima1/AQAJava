package org.example.task4.breakContinue;

import java.util.Scanner;

public class OnlyPositiveNumbers {
    public static void main(String[] args) {
        numbers();

    }

    public static void numbers() {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            number = scanner.nextInt();
            if (number > 0) {
                System.out.println(number);
            } else {
                System.out.println("Вы ввели не положительное число");
                continue;
            }
        } while (number != 0);
    }
}
