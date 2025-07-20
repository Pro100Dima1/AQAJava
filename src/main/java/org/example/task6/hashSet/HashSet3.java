package org.example.task6.hashSet;

import java.util.*;

public class HashSet3 {
    public static void main(String[] args) {
        List<String> string = new LinkedList<>(Arrays.asList("a", "b", "c", "c", "123", "c", "a"));
        Set<String> res = returnSet(string);
        System.out.println(res);
    }

    public static Set<String> returnSet(List list) {
        HashSet<String> setString = new HashSet<>();
        setString.addAll(list);
        return setString;
    }
}
