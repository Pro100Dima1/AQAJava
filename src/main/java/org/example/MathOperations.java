package org.example;

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

    static double convertSecondsToMinutes(int seconds)
}
