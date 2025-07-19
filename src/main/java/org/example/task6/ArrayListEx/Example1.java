package org.example.task6.ArrayListEx;

import java.util.ArrayList;
import java.util.Arrays;

public class Example1 {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        arrayList.add(5);
        System.out.println(arrayList);
    }
}
