package org.example.task8.aggregatingOperations;

import java.util.List;

public class SummationNumbers {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = List.of(1, 2, 3, 4, 5);
        int summ = listOfNumbers.stream()
                .mapToInt(x -> x)
                .sum();
        System.out.println(summ);
    }
}
