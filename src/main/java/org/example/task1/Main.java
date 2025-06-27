package org.example.task1;

import static org.example.task1.MathOperations.*;

public class Main {
    public static void main(String[] args) {
        int add = add(1, 8);
        int sub = subtract(8, 5);
        int mul = multiply(1, 8);
        double div = divide(1.2, 8.3);
        System.out.println(add);
        System.out.println(sub);
        System.out.println(mul);
        System.out.println(div);

        int max = findMax(3, 9);
        System.out.println(max);

        int mod = difference(2, 9);
        System.out.println(mod);

        int area = squareArea(4);
        System.out.println(area);
        int perimiter = squarePerimeter(8);
        System.out.println(perimiter);

        double min = convertSecondsToMinutes(137);
        System.out.println(min);

        double averageSpeed1 = averageSpeed(123, 23);
        double averageSpeed2 = averageSpeed(23, 123);
        System.out.println(averageSpeed1);
        System.out.println(averageSpeed2);

        double hypotenuse = findHypotenuse(4, 4);
        double hypotenuse2 = findHypotenuse(5, 5);
        System.out.println(hypotenuse);
        System.out.println(hypotenuse2);

        double radius = circleCircumference(5);
        double radius2 = circleCircumference(8);
        System.out.println(radius);
        System.out.println(radius2);

        double percent = calculatePercentage(0, 100);
        double percent2 = calculatePercentage(100, 25);
        System.out.println(percent);
        System.out.println(percent2);

        double far = fahrenheitToCelsius(4);
        double cel = celsiusToFahrenheit(4);
        System.out.println(far);
        System.out.println(cel);
    }
}