package org.expressJava.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class TransformStringToInteger {
    public static void main(String[] args) {
        List<String> name = List.of("Pasha", "Nadya", "Senya", "Zuhra", "M", "Qwertt");
        List<Integer> lengthOfName = name.stream()
                .map(str -> str.length())
                .collect(Collectors.toList());
        System.out.println(lengthOfName);
    }
}
