package org.expressJava.task11;

import java.util.*;

public class DebugTask10 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();

            if (name.startsWith("A")) {
                iterator.remove(); // Безопасное удаление элемента через итератор
            }
        }

        System.out.println(names);
    }
}