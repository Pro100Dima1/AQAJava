package org.example.task2;

public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    void print(){
        System.out.println("Координата х: " + this.x + ". Координата y: " + this.y);
    }
}
