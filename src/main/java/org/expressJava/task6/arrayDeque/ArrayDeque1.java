package org.expressJava.task6.arrayDeque;

import java.util.ArrayDeque;

public class ArrayDeque1 {
    public static void main(String[] args) {
        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        numbers.addLast(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(4);
        numbers.addLast(5);
        while (!numbers.isEmpty()){
            System.out.println(numbers.removeFirst());
        }
    }
}
