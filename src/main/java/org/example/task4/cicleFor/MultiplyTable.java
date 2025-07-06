package org.example.task4.cicleFor;

import java.util.Scanner;

public class MultiplyTable {
    public static void main(String[] args) {
        table();
    }

    public static void table() {
        Scanner scanner = new Scanner(System.in);
        int numberOfTable = scanner.nextInt();

        for (int i = 0; i <= numberOfTable; i++) {
            System.out.println(i + " Х " + numberOfTable + " = " + (i * numberOfTable));
        }
    }
}
