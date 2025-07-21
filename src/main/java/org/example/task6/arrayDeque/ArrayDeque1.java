package org.example.task6.arrayDeque;

import java.util.ArrayDeque;

public class ArrayDeque1 {
    public static void main(String[] args) {
        ArrayDeque<Integer> ex1 = new ArrayDeque();
        ex1.addLast(1);
        ex1.addLast(2);
        ex1.addLast(3);
        ex1.addLast(4);
        ex1.addLast(5);
        while (!ex1.isEmpty()){
            System.out.println(ex1.removeFirst());
        }
    }
}
