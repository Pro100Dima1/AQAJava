package org.example.task6.hashSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashSet4 {
    public static void main(String[] args) {
        Set<String> name = new HashSet<>(Arrays.asList("Misha", "Masha", "Dima", "Vitya"));
        String personName = "Dima";
        if(name.contains(personName)){
            System.out.println("В множестве есть " + personName);
        }else {
            System.out.println("Вас нет в списке");
        }
    }
}
