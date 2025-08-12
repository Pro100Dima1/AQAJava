package org.example.task8.aggregatingOperations;

import java.util.Arrays;
import java.util.List;

public class SummationNumbers {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int summation = listOfNumbers.stream()
                .mapToInt(x -> x)
                .sum();
        System.out.println(summation);
    }
}
