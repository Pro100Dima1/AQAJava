package org.example.task6.hashMap;

import java.util.HashMap;

public class HashMap2 {
    public static void main(String[] args) {
        HashMap<Integer, String> employee = new HashMap<>();
        employee.put(1, "Ivan");
        employee.put(2, "Egor");
        employee.put(3, "Pavel");
        System.out.println(employee.containsValue("Liza"));
        System.out.println(employee.containsKey(2));
    }
}
