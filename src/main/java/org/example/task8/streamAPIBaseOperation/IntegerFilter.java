package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class IntegerFilter {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 22, 35, 41, 55, 68);
        List<Integer> filteredNumbers = numbers.stream()
                .filter(num -> num % 5 == 0)
                .collect(Collectors.toList());

        System.out.println(filteredNumbers);
    }
}
