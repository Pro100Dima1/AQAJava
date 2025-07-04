package org.example.task4.switchCase;

import java.util.Scanner;

public class NumberToLetter {
    public static void main(String[] args) {
        System.out.println(letter());

    }

    public static String letter() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        String letter = "";
        if (number < 100 && number > 90) {
            letter = "A";
        } else if (number < 89 && number > 80) {
            letter = "B";
        } else if (number < 79 && number > 70) {
            letter = "C";
        } else if (number < 69 && number > 60) {
            letter = "D";
        } else if (number < 60) {
            letter = "F";
        }
        return letter;
    }
}
