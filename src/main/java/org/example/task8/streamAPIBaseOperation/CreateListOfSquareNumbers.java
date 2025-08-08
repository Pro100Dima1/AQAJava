package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.stream.Collectors;

public class CreateListOfSquareNumbers {
    public static void main(String[] args) {
        List<Integer> listOfNimber = List.of(1, 2, 3, 4, 5);
        List<Integer> listOfSquareNumbers = listOfNimber.stream()
                .map(numb -> numb * numb)
                .collect(Collectors.toList());
        System.out.println(listOfSquareNumbers);
    }
}
