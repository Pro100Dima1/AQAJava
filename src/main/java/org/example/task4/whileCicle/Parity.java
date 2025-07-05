package org.example.task4.whileCicle;

import java.util.Scanner;

public class Parity {
    public static void main(String[] args) {
        checkParity();
    }

    public static void checkParity() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        int i = 1;
        while (i <= number) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
            i++;
        }
    }
}
