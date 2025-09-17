package org.expressJava.task6.linkedHashMap;

import java.util.LinkedHashMap;

public class LinkedHashMap1 {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> numbers = new LinkedHashMap<>();
        numbers.put(1, "1");
        numbers.put(2, "2");
        numbers.put(4, "4");
        numbers.put(3, "3");
        numbers.put(5, "5");
        System.out.println(numbers);
    }
}
