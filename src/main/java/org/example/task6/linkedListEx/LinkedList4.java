package org.example.task6.linkedListEx;

import java.util.LinkedList;

public class LinkedList4 {
    public static void main(String[] args) {
        LinkedList<Integer> ex4 = new LinkedList<>();
        ex4.add(3);
        ex4.add(3);
        ex4.add(3);
        ex4.add(3);
        ex4.add(3);

        int sum = 0;
        for (int i : ex4) {
            sum += i;
        }
        System.out.println(sum);
    }
}
