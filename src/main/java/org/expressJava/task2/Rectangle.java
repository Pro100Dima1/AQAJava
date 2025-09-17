package org.expressJava.task2;

public class Rectangle {
    int width;
    int height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    void setWidth(int width) {
        this.width = width;
    }

    int calculateArea(){
        int area = width * height;
        return area;
    }
}
