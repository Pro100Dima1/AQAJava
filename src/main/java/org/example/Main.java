package org.example;

import static org.example.MathOperations.*;

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
    }
}