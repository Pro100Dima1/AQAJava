package org.expressJava.task6.linkedHashSet;

import java.util.LinkedHashSet;

public class LinkedHashSet2 {
    public static void main(String[] args) {
        LinkedHashSet<String> string = new LinkedHashSet<>();
        System.out.println(example(string));
    }

    public static LinkedHashSet<String> example(LinkedHashSet<String> string) {
        string.add("Qwerty");
        string.add("Qwerty");
        string.add("Qwerty");
        string.add("Rostelekom");
        string.add("Rostelekom");
        string.add("Rostelekom");
        string.add("DomRu");
        return string;
    }
}
