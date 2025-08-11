package org.example.task8.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AverageOfNumber {
    public static void main(String[] args) {
        List<Integer> numb = Arrays.asList(1, 2, 3, 4, 5, 6);
        double avgOfNumb = numb.stream()
                .collect(Collectors.averagingInt(x -> x));
        System.out.println(avgOfNumb);
    }
}
