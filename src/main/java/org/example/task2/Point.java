package org.example.task2;

public class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    void setX(int x){
        this.x = x;
    }

    void print(){
        System.out.println("Координата х = " + this.x + ". Координата у = " + this.y);
    }
}
