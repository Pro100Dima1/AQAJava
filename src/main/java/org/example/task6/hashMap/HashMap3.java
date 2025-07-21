package org.example.task6.hashMap;

import java.util.HashMap;
import java.util.Map;

public class HashMap3 {
    public static void main(String[] args) {
        HashMap<String, Integer> person = new HashMap<>();
        person.put("Dima", 21);
        person.put("Zina", 24);
        person.put("Mitya", 10);
        person.put("Polina", 19);
        person.put("Zuhra", 18);
        person.put("Abdul", 11);
        person.put("Masha", 29);
        person.put("Petr", 42);
        person.put("Fedya", 17);

        printYoung(person);
    }

    public static void printYoung(HashMap<String, Integer> person) {
        for (int i : person.values()) {
            if (i <= 18) {
                System.out.println(i);
            }
        }
        for (Map.Entry<String, Integer> entry : person.entrySet()) {
            if (entry.getValue() <= 18) {
                System.out.println(entry.getKey() + " " + entry.getValue() + " лет");
            }
        }
    }
}
