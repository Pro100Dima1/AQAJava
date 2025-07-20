package org.example.task6.hashMap;

import java.util.HashMap;

public class HashMap2 {
    public static void main(String[] args) {
        HashMap<String, Integer> personInfo = new HashMap<>();
        personInfo.put("Liza", 10);
        personInfo.put("Sonya", 14);
        personInfo.put("Ira", 15);
        personInfo.put("Olya", 12);
       System.out.println(personInfo.containsKey("Liza"));
       System.out.println(personInfo.containsKey("Lizza"));
    }
}
