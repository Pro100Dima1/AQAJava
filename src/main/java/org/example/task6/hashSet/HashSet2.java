package org.example.task6.hashSet;

import java.util.HashSet;

public class HashSet2 {
    public static void main(String[] args) {
        HashSet<Integer> tenNumbers = new HashSet<>();
        tenNumbers.add(1);
        tenNumbers.add(2);
        tenNumbers.add(3);
        tenNumbers.add(4);
        tenNumbers.add(5);
        tenNumbers.add(6);
        tenNumbers.add(7);
        tenNumbers.add(8);
        tenNumbers.add(9);
        tenNumbers.add(10);
        System.out.println(tenNumbers.contains(4));
    }
}
