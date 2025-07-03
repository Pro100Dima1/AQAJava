package org.example.task4.ifElse;

import java.util.Scanner;

public class Score {
    public static void main(String[] args) {
        System.out.print("Введите полученную оценку ");
        System.out.print(score());

    }

    public static String score() {
        Scanner score = new Scanner(System.in);
        int currentScore = score.nextInt();

        String mathScore = "";
        switch (currentScore) {
            case 1:
                mathScore = "Неудовлетворительно";
                break;
            case 2:
                mathScore = "Неудовлетворительно";
                break;
            case 3:
                mathScore = "Удовлетворительно";
                break;
            case 4:
                mathScore = "Хорошо";
                break;
            case 5:
                mathScore = "Отлично";
                break;
        }
        return mathScore;
    }
}
