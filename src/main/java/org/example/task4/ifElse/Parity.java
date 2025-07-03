package org.example.task4.ifElse;

import java.util.Scanner;

public class Parity {
    public static void main(String[] args) {
        System.out.println(parityOfNumber());
    }

    public static String parityOfNumber() {
        Scanner enterNumber = new Scanner(System.in);
        int number = enterNumber.nextInt();

        String parity = (number % 2 == 0) ? "Четное" : "Нечетное";
        return parity;
    }
}
