package org.example.task8.aggregatingOperations;

import java.util.Comparator;
import java.util.List;

public class FindMinElement {
    public static void main(String[] args) {
        List<Integer> listOfNumber = List.of(5, 3, 7, 6, 3, 6, 3, 1);
        int minNumber = listOfNumber.stream()
                .min(Comparator.naturalOrder())
                .get();

        System.out.println(minNumber);
    }
}
