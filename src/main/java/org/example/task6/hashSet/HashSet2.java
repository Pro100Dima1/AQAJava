package org.example.task6.hashSet;

import java.util.HashSet;

public class HashSet2 {
    public static void main(String[] args) {
        HashSet<Integer> ex2 = new HashSet<>();

        ex2.add(1);
        ex2.add(2);
        ex2.add(3);
        ex2.add(4);
        ex2.add(5);
        ex2.add(6);
        ex2.add(7);
        ex2.add(8);
        ex2.add(9);
        ex2.add(10);

        System.out.println(ex2.contains(5));
    }
}
