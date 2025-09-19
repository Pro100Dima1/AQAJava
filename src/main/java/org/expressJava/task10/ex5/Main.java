package org.expressJava.task10.ex5;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
