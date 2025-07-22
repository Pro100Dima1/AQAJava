package org.example.task6.hashSet;

import java.util.HashSet;

public class HashSet1 {
    public static void main(String[] args) {
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(7);
        numbers.add(18);
        numbers.add(1);
        System.out.println(numbers);
    }
}
