package org.example.task6.treeMap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMap1 {
    public static void main(String[] args) {
        TreeMap<String, Integer> studentsScore = new TreeMap<>();
        studentsScore.put("Ivan", 1);
        studentsScore.put("Olya", 7);
        studentsScore.put("Zzenya", 6);
        studentsScore.put("Zaenya", 6);
        studentsScore.put("Vera", 2);
        studentsScore.put("Slava", 3);
        System.out.println(studentsScore);
    }
}

