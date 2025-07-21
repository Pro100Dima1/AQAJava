package org.example.task6.linkedHashMap;

import java.util.LinkedHashMap;

public class LinkedHashMap1 {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> students = new LinkedHashMap<>();
        students.put("Zera", 12);
        students.put("Zina", 18);
        students.put("Zaur", 14);
        students.put("Alina", 16);
        students.put("Msrusya", 146);
        System.out.println(students);
    }
}
