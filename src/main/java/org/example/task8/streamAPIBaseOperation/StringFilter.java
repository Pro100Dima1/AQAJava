package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class StringFilter {
    public static void main(String[] args) {
        List<String> listOfString = List.of("Qwerty", "Banan", "SOS", "RAK");
        List<String> filteredList = listOfString.stream()
                .filter(str -> str.length() < 5)
                .collect(Collectors.toList());
        System.out.println(filteredList);
    }
}
