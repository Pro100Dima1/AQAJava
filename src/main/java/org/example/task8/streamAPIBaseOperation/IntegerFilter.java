package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class IntegerFilter {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 15, 48, 35);
        List<Integer> filteredNumbers = numbers.stream()
                .filter(x -> x % 5 == 0)
                .collect(Collectors.toList());
        System.out.println(filteredNumbers);
    }
}
