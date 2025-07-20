package org.example.task6.treeSet;

import java.util.TreeSet;

public class TreeSet2 {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        System.out.println(addUniqNumber(numbers));
    }

    public static TreeSet<Integer> addUniqNumber(TreeSet<Integer> num) {
        num.add(1);
        num.add(12);
        num.add(1);
        num.add(7);
        return num;
    }
}
