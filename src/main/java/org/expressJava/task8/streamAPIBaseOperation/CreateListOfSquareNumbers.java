package org.expressJava.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class CreateListOfSquareNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> aquareNumbers = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(aquareNumbers);
    }
}
