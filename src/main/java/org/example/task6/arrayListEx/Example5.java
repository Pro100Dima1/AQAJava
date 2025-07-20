package org.example.task6.arrayListEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Example5 {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 5, 4, 3));

        int max = 0;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);

        int max2 = Collections.max(arr);
        System.out.println(max2);
    }
}
