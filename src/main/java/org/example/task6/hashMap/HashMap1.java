package org.example.task6.hashMap;

import java.util.HashMap;

public class HashMap1 {
    public static void main(String[] args) {
        HashMap<String, Integer> students = new HashMap<>();
        students.put("Ivan", 4);
        students.put("Dima", 3);
        students.put("Jora", 4);
        students.put("Ilya", 1);
        students.put("Nikita", 6);
        System.out.println(students);
    }
}
