package org.example.task4.ifElse;

import org.example.task2.Rectangle;

import java.util.Scanner;

public class Score {
    public static void main(String[] args) {
        System.out.println(score());
    }

    public static String score() {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();

        String currentScore = "";
        if (score == 5) {
            currentScore = "Отлично";
        }
        if (score == 4) {
            currentScore = "Хорошо";
        }
        if (score == 3) {
            currentScore = "Удовлетворительно";
        }
        if (score == 2 || score == 1) {
            currentScore = "Неудовлетворительно";
        }
        return currentScore;
    }
}
