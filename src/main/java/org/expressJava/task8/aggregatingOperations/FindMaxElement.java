package org.expressJava.task8.aggregatingOperations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindMaxElement {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 89, 3, 2, 102, 99, 10);
        int maxNumber = listOfNumbers.stream()
                .max(Comparator.naturalOrder())
                .get();
        System.out.println(maxNumber);
    }
}
