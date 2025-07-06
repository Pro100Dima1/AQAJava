package org.example.task4.cicleFor;

import java.util.Scanner;

public class OneToN {
    public static void main(String[] args) {
        mathProgression();
    }

    public static void mathProgression() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            System.out.println(sum);
        }
    }
}
