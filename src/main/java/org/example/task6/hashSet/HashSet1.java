package org.example.task6.hashSet;

import java.util.HashSet;

public class HashSet1 {
    public static void main(String[] args) {
        HashSet<Integer> ex1 = new HashSet<>();

        ex1.add(1);
        ex1.add(1);
        ex1.add(2);
        ex1.add(2);
        ex1.add(3);
        ex1.add(4);
        ex1.add(5);

        System.out.println(ex1);
    }
}
