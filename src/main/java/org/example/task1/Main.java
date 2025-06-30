package org.example.task1;

import static org.example.task1.MathOperations.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(add(5, 2));
        System.out.println(subtract(8, 14));
        System.out.println(multiply(4, 7));
        System.out.println(divide(36, 8));
        System.out.println("Большее из двух чисел = " + findMax(36, 8));
        System.out.println("Модуль = " + difference(8, 48));
        System.out.println("Площадь квадрата = " + squareArea(8));
        System.out.println("Периметр квадрата = " + squarePerimeter(8));
        System.out.println("Количество минут = " + convertSecondsToMinutes(800));
        System.out.println("Скорость 1-го автомобиля =  " + averageSpeed(800, 30));
        System.out.println("Скорость 2-го автомобиля = " + averageSpeed(500, 60));
        System.out.println("Гипотенуза = " + findHypotenuse(30, 60));
        System.out.println("Окружность Земли = " + circleCircumference(12000));
        System.out.println("Окружность Марса = " + circleCircumference(7000));
        System.out.println("Процент = " + calculatePercentage(0, 20));
        System.out.println("Фарнегейт = " + celsiusToFahrenheit(17));
        System.out.println("Цельсия = " + fahrenheitToCelsius(73));
    }
}