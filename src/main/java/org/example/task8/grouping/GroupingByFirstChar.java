package org.example.task8.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByFirstChar {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Qwerty", "Aqa", "Qazwsx", "Awawa");
        Map<String, List<String>> groupingWords = words.stream()
                .collect(Collectors.groupingBy(word -> word.substring(0,1)));
        System.out.println(groupingWords);
    }
}
