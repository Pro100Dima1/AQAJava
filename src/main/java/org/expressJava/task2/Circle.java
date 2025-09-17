package org.expressJava.task2;

public class Circle {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double getRadius() {
        return this.radius;
    }

    void setRadius(double radius) {
        this.radius = radius;
    }

    double calculateArea() {
        double areaOfCircle = Math.PI * Math.pow(this.radius, 2);
        return Math.round(areaOfCircle);
    }

    double calculateCircumference() {
        double circumferenceOfCircle = 2 * Math.PI * this.radius;
        return Math.round(circumferenceOfCircle);
    }

}
