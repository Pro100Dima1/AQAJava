package org.example.task2;

public class Circle {
    double radius;

    Circle (int radius){
        this.radius = radius;
    }

    double getRadius(){
        return this.radius;
    }

    void setRadius(double radius){
        this.radius = radius;
    }

    double calculateArea(){
        double area = Math.PI * Math.pow(radius, 2);
        System.out.println("Площадь круга: " + area);
        return area;
    }

    double calculateCircumference(){
        double circumference = 2 * Math.PI * radius;
        System.out.println("Длинна окружности: " + circumference);
        return circumference;
    }
}
