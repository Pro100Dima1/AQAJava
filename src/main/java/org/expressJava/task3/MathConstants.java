package org.expressJava.task3;

public class MathConstants {
    static final double PI = 3.14159;
    static final double E = 2.71828;

    static double calculateCircleArea(double r) {
        double areaCircle = PI * Math.pow(r, 2);
        return areaCircle;
    }

    static double calculateCircumference(double r) {
        double circumference = 2 * PI * r;
        return circumference;
    }
}
