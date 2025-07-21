package org.example.task6.arrayDeque;

import java.util.ArrayDeque;

public class ArraDeque2 {
    public static void main(String[] args) {
        ArrayDeque<Integer> ex2 = new ArrayDeque();
        ex2.push(1);
        ex2.push(2);
        ex2.push(3);
        ex2.push(4);
        ex2.push(5);
        while (!ex2.isEmpty()){
            System.out.println(ex2.pop());
        }
    }
}
