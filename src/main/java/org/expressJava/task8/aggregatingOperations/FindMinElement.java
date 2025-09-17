package org.expressJava.task8.aggregatingOperations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindMinElement {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 99, 2);
        int minNumber = listOfNumbers.stream()
                .min(Comparator.naturalOrder())
                .get();
        System.out.println(minNumber);
    }
}
