package org.example.task6.treeMap;

import java.util.TreeMap;

public class TreeMap3 {
    public static void main(String[] args) {
        TreeMap<Integer, String> employee = new TreeMap<>();
        int highKey = 5;
        employee.put(1, "Q");
        employee.put(13, "W");
        employee.put(3, "E");
        employee.put(8, "R");
        employee.put(5, "T");
        employee.put(7, "Y");
        employee.put(10, "Z");
        System.out.println(employee.higherKey(highKey));
        System.out.println(employee.lowerKey(highKey));
    }
}
