package org.expressJava.reshebnik.firstProgramm;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumTwoNumber(2, 7));
        System.out.println(subtract(2, 7));
    }

    public static int sumTwoNumber(int firstNumber, int secondNumber) {
        int sumResult = firstNumber + secondNumber;
        return sumResult ;
    }

    public static int subtract(int firstNumber, int secondNumber) {
        int sumResult = firstNumber - secondNumber;
        return sumResult ;
    }
}
