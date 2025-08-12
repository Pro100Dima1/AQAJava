package org.example.task8.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingNumberByEven {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> mapOfEvenNumbers = numbers.stream()
                .collect(Collectors.groupingBy(x -> x % 2 == 0));
        System.out.println(mapOfEvenNumbers);
    }
}
