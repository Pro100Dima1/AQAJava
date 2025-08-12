package org.example.task8.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AverageOfNumber {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 2, 2, 2);
        double averageNumber = numbers.stream()
                .collect(Collectors.averagingDouble(n -> n));
        System.out.println(averageNumber);
    }
}
