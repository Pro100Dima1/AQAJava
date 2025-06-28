package org.example.task2;

public class Rectangle {
    int width;
    int height;

    Rectangle(int width, int height){
        this.height = height;
        this.width = width;
    }

    int getWidth(){
        return this.width;
    }

    int getHeight(){
        return this.height;
    }

    void setWidth(int newWidth){
        this.width = newWidth;
    }

    int calculateArea(int width, int height){
        int area = width * height;
        return area;
    }
}
