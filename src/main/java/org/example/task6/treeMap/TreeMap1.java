package org.example.task6.treeMap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMap1 {
    public static void main(String[] args) {
        TreeMap<String, Integer> ex1 = new TreeMap<>();
        ex1.put("Bob", 15);
        ex1.put("Boa", 17);
        ex1.put("Bot", 13);
        ex1.put("Boz", 11);
        ex1.put("Box", 23);
        for(Map.Entry<String, Integer> entry : ex1.entrySet()){
            System.out.println("Имя: " + entry.getKey() + " Возраст: " + entry.getValue());
        }
    }
}
