package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class TransformStringToInteger {
    public static void main(String[] args) {
        List<String> name = List.of("Katya", "Mish1a", "Dima", "Eugeniy");
        List<Integer> lengthOfName = name.stream()
                .map(words -> words.length())
                .collect(Collectors.toList());
        System.out.println(lengthOfName);
    }
}
