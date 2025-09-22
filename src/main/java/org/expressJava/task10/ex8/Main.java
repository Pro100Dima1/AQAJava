package org.expressJava.task10.ex8;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int findSecondMax(int[] numbers) {
        return Arrays.stream(numbers).distinct().sorted().skip(numbers.length - 2).findFirst().orElseThrow();
    }
}
