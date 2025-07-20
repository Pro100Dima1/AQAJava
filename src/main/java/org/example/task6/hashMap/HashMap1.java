package org.example.task6.hashMap;

import java.util.HashMap;

public class HashMap1 {
    public static void main(String[] args) {
        HashMap<String, Integer> personInfo = new HashMap<>();
        personInfo.put("Sasha", 18);
        personInfo.put("Nikita", 28);
        personInfo.put("Tanya", 23);
        personInfo.put("Miron", 17);
        personInfo.put("Timur", 41);
        System.out.println(personInfo);
    }
}
