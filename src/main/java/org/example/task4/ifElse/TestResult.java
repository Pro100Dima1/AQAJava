package org.example.task4.ifElse;

import java.util.Scanner;

public class TestResult {
    public static void main(String[] args) {
        System.out.println(calculateTestResult());
    }

    public static String calculateTestResult() {
        Scanner scanner = new Scanner(System.in);
        int testResult = scanner.nextInt();

        String statement = "";
        if (testResult >= 0 && testResult <= 100) {
            if (testResult >= 90) {
                statement = "Отлично";
            } else if (testResult <= 89 && testResult >= 75) {
                statement = "Хорошо";
            } else if (testResult <= 74 && testResult >= 60) {
                statement = "Удовлетворительно";
            } else if (testResult < 60) {
                statement = "Неудовлетворительно";
            }
        }
        else {
            System.out.println("Ваш балл выходит за рамки экзамена");
        }
        return statement;
    }
}
