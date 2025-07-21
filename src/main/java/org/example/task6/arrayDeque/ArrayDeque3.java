package org.example.task6.arrayDeque;

import java.util.ArrayDeque;

public class ArrayDeque3 {
    public static void main(String[] args) {
        ArrayDeque<Integer> ex3 = new ArrayDeque<>();
        ex3.addFirst(1);
        ex3.addLast(10);
        System.out.println(ex3.removeFirst());
        System.out.println(ex3.removeLast());
    }
}
