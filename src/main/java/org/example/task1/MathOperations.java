package org.example.task1;

public class MathOperations {

    static int add(int x, int y) {
        int sum = x + y;
        return sum;
    }

    static int subtract(int x, int y) {
        int sub = x - y;
        return sub;
    }

    static int multiply(int x, int y) {
        int mul = x * y;
        return mul;
    }

    static int divide(int x, int y) {
        int div = x / y;
        return div;
    }

    static int findMax(int a, int b) {
        int max = Math.max(a, b);
        return max;
    }

    static int difference(int x, int y) {
        int module = Math.abs(x - y);
        return module;
    }

    static int squareArea(int side) {
        int squareArea = side * side;
        return squareArea;
    }

    static int squarePerimeter(int side) {
        int squarePerimeter = side * 4;
        return squarePerimeter;
    }

    static double convertSecondsToMinutes(int seconds) {
        double minutes = seconds / 60;
        return minutes;
    }

    static double averageSpeed(double distance, double time) {
        double averageSpeed = distance / time;
        return averageSpeed;
    }

    static double findHypotenuse(double a, double b) {
        double hypotenuse = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return hypotenuse;
    }

    static double circleCircumference(double radius) {
        double circle = 2 * Math.PI * radius;
        return circle;
    }

    static double calculatePercentage(double total, double part) {
        if (total <= 0) {
            System.out.println("На ноль делить можно, но не сегодня, дружок");
        } else {
            double percent = (part / total) * 100;
            return percent;
        }
        return total;
    }

    static double celsiusToFahrenheit(double c) {
        double far = c * 9 / 5 + 32;
        return far;
    }

    static double fahrenheitToCelsius(double f) {
        double cel = (f - 32) * 5 / 9;
        return cel;
    }


}
