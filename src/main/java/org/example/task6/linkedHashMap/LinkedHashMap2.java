package org.example.task6.linkedHashMap;

import java.util.LinkedHashMap;

public class LinkedHashMap2 {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> phoneBook = new LinkedHashMap<>();
        phoneBook.put("ЖЭК", 666);
        phoneBook.put("Ветиринарка", 713);
        phoneBook.put("Рэсторан", 118);
        phoneBook.put("Офис", 792);
        String name = "ЖЭК";
        System.out.println(phoneBook.get(name));
    }
}
