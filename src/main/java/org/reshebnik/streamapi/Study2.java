package org.reshebnik.streamapi;

import java.util.stream.Stream;

public class Study2 {
    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    public static void task2() {
        Stream<Integer> stream =
                Stream.of(1, 2, 3, 4, 5).filter(i -> i % 2 != 0);
        stream.forEach(System.out::println);
        System.out.println(stream.reduce(5, Integer::sum));
    }
}
