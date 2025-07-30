package org.example.task7.genericPrintArr;

public class Arr {
    public static void main(String[] args) {
        Integer[] arrayInt = {1, 2, 3};
        String[] arrayString = {"Qwerty", "MTS"};
        printArray(arrayInt);
        printArray(arrayString);
    }

    public static <A> void printArray(A[] array) {
        for (A element : array) {
            System.out.println(element);
        }
    }
}
