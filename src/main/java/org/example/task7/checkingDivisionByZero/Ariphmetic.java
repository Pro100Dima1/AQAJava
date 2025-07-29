package org.example.task7.checkingDivisionByZero;

public class Ariphmetic {
    public static void main(String[] args) {
        division(2, 0   );
    }

    public static void division(int divisible, int divider) {
        try {
            int result = divisible / divider;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Делить на ноль, увы можно. Но не сегодня");
        }
    }
}
