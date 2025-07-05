package org.example.task4.dowhile;

import java.util.Scanner;

public class CountNumbers {
    public static void main(String[] args) {
        countNumbers();
    }

    public static void countNumbers() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int count = 0;

        do {
            number = number / 10;
            count++;
            System.out.println(count);
        } while (number != 0);
    }
}
