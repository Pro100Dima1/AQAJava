package org.example.task4.switchCase;

import java.util.Scanner;

public class DayOfWeek {
    public static void main(String[] args) {
        printDayOfWeek();

    }

    public static void printDayOfWeek() {
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();
            switch (days) {
                case 1 -> System.out.println("Понедельник");
                case 2 -> System.out.println("Вторник");
                case 3 -> System.out.println("Среда");
                case 4 -> System.out.println("Четверг");
                case 5 -> System.out.println("Пятница");
                case 6 -> System.out.println("Суббота");
                case 7 -> System.out.println("Воскресенье");
                default -> System.out.println("Увы, в неделе всего 7 дней");
            }
        }
}
