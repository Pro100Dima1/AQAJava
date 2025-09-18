package org.expressJava.task10.ex4;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int findMax(int[] numbers) {
        return Arrays.stream(numbers).max().orElseThrow();
    }

}
