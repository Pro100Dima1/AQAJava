package org.expressJava.task6.arrayListEx;

import java.util.ArrayList;
import java.util.Arrays;

public class Example1 {

    public static void main(String[] args) {
        ArrayList<Integer> arrays = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        arrays.add(6);
        System.out.println(arrays);
    }
}
