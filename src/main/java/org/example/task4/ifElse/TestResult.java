package org.example.task4.ifElse;

import java.util.Scanner;

public class TestResult {
    public static void main(String[] args) {
        System.out.println(calculateTestResult());
    }

    public static String calculateTestResult() {
        Scanner scanner = new Scanner(System.in);
        int testScore = scanner.nextInt();

        String statement = "";
        if (testScore >= 90) {
            statement = "Отлично";
        } else if (testScore <= 89 && testScore >= 75) {
            statement = "Хорошо";
        } else if (testScore <= 74 && testScore >= 60) {
            statement = "Удовлетворительно";
        } else if (testScore < 60) {
            statement = "Неудовлетворительно";
        }
        return statement;
    }
}
