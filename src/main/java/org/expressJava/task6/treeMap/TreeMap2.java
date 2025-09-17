package org.expressJava.task6.treeMap;

import java.util.TreeMap;

public class TreeMap2 {
    public static void main(String[] args) {
        TreeMap<Integer, String> ex2 = new TreeMap<>();
        ex2.put(1, "1");
        ex2.put(4, "2");
        ex2.put(2, "3");
        ex2.put(8, "4");
        ex2.put(3, "5");
        System.out.println(ex2.firstKey());
        System.out.println(ex2.lastKey());
    }
}
