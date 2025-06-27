package org.example.task1;

public class MathOperations {

    static int add(int x, int y) {
        int resAdd = x + y;
        return resAdd;
    }

    static int subtract(int x, int y) {
        int resSubstract = x - y;
        return resSubstract;
    }

    static int multiply(int x, int y) {
        int resMultiply = x * y;
        return resMultiply;
    }

    static double divide(double x, double y) {
        double resDivide = x / y;
        return resDivide;
    }

    static int findMax(int a, int b) {
        int res = Math.max(a, b);
        return res;
    }

    static int difference(int x, int y) {
        int mod = Math.abs(x - y);
        return mod;
    }

    static int squareArea(int side) {
        int area = side * side;
        return area;
    }

    static int squarePerimeter(int side) {
        int perimiter = side * 4;
        return perimiter;
    }

    static double convertSecondsToMinutes(int seconds) {
        double minute = seconds / 60;
        return minute;
    }

    static double averageSpeed(double distance, double time) {
        double avSpeed = distance / time;
        return avSpeed;
    }

    static double findHypotenuse(double a, double b) {
        double hyptenuse = Math.sqrt(Math.pow(a, a) + Math.pow(b, b));
        return hyptenuse;
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
