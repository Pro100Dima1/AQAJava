package org.example.task8.aggregatingOperations;

import java.util.Comparator;
import java.util.List;

public class FindMaxElement {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = List.of(1, 2, 3, 199, 5, 923, 29, 81);

        int maxNumber = listOfNumbers.stream()
                .max(Comparator.naturalOrder())
                .get();

        System.out.println(maxNumber);
    }
}
