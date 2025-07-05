package org.example.task4.cicleFor;

import java.util.Scanner;

public class OneToTen {
    public static void main(String[] args) {
        numberSeries();
    }

    public static void numberSeries() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }
}
