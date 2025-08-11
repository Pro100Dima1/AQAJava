package org.example.task8.aggregatingOperations;

import java.util.Arrays;
import java.util.List;

public class FindSomething {
    public static void main(String[] args) {
        List<Integer> numb = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean isHasEvenNumber = numb.stream()
                .anyMatch(x -> x % 2 == 0);
        System.out.println(isHasEvenNumber);
    }
}
