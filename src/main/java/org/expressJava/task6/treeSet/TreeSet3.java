package org.expressJava.task6.treeSet;

import java.util.Arrays;
import java.util.TreeSet;

public class TreeSet3 {
    public static void main(String[] args) {
        TreeSet<Integer> firstMinMax = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        System.out.println(firstMinMax.lower(4));
        System.out.println(firstMinMax.higher(4));
    }
}
