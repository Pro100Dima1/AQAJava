package org.example.task6.arrayListEx;

import java.util.ArrayList;
import java.util.Arrays;

public class Example2 {
    public static void main(String[] args) {
        ArrayList<Integer> ex2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        for (int i : ex2) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
