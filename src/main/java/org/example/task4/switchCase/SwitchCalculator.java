package org.example.task4.switchCase;

import java.util.Scanner;

public class SwitchCalculator {
    public static void main(String[] args) {
        System.out.println(calculator());
    }

    public static int calculator() {
        Scanner scanner = new Scanner(System.in);
        String operator = scanner.nextLine();
        int firstNumber = scanner.nextInt();
        int secondNumber = scanner.nextInt();

        int result = 0;
        if (secondNumber == 0 && operator.equals("/")) {
            System.out.println("Делить на ноль можно, но не сегодня");
        } else {
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                default:
                    System.out.println("Вышел за рамки возможностей калькулятора");
            }
        }
        return result;
    }
}
