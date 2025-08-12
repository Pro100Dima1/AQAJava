package org.example.task8.aggregatingOperations;

import java.util.Arrays;
import java.util.List;

public class FindFirstChar {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Оля", "Гога", "Борис", "Бадьян");
        String name = names.stream()
                .filter(n -> n.startsWith("Б"))
                .findFirst()
                .get();
        System.out.println(name);
    }
}
