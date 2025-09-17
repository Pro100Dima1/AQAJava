package org.expressJava.task4.ifElse;

import java.util.Scanner;

public class FindMaxNumber {
    public static void main(String[] args) {
        System.out.println(findMaxNumber());
    }

    public static int findMaxNumber() {
        Scanner scanner = new Scanner(System.in);
        int firstNumber = scanner.nextInt();
        int secondNumber = scanner.nextInt();

        int maxNumber = 0;
        if (firstNumber > secondNumber) {
            maxNumber = firstNumber;
        }
        if (secondNumber > firstNumber) {
            maxNumber = secondNumber;
        }
        return maxNumber;
    }
}
