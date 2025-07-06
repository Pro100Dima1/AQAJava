package org.example.task4.whileCicle;

import java.util.Date;
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        factorailNumber();
    }

    public static void factorailNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        int i = 1;
        int result = 1;
        while (i <= number) {
            result *= i;
            System.out.println(result);
            i++;
        }
    }
}
