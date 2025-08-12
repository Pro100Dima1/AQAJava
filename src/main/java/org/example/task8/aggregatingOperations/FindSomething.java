package org.example.task8.aggregatingOperations;

import java.util.Arrays;
import java.util.List;

public class FindSomething {
    public static void main(String[] args) {
        List<Integer> listNotEveNumber = Arrays.asList(1, 2, 3, 5);
        boolean isEvenNumber = listNotEveNumber.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println(isEvenNumber);
    }
}
