package org.example.task6.arrayListEx;

import java.util.ArrayList;
import java.util.Arrays;

public class Example2 {
    public static void main(String[] args) {
        ArrayList<Integer> chetniyArray = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        for (int i : chetniyArray) {
            if (i % 2 == 0) {
                System.out.println("Четные числа " + i);
            }
        }
    }
}
