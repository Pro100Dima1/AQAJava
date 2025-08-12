package org.example.task8.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByFirstChar {
    public static void main(String[] args) {
        List<String> listOfString = Arrays.asList("Qwert", "MTS", "LTE", "MGTS", "QAZWSX");
        Map<String, List<String>> groupingList = listOfString.stream()
                .collect(Collectors.groupingBy(str -> str.substring(0, 1)));
        System.out.println(groupingList);
    }
}
